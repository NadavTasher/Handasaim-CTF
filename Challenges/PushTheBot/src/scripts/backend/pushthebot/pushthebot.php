<?php

include_once __DIR__ . "/../base/api.php";

/**
 * Specification:
 * Decoded code should be in the following format:
 * {
 *  teamnamehash: TeamNameSHA1
 *  codes:[
 *    {
 *     selectpin: PinNumber
 *     pinmode: input, output
 *     command: digitalWrite, analogWrite, servoWrite
 *     parameter: 0-180
 *     enable: true,
 *     length: DelayInMs
 *    }
 *  ]
 * }
 */

api("pushthebot", function ($action, $parameters) {
    if ($action === "pushcode") {
        if (isset($parameters->code) && isset($parameters->team)) {
            $parsed = ptb_parse($parameters->code);
            if ($parsed !== null) {
                if (isset($parsed->teamnamehash)) {
                    if ($parsed->teamnamehash === sha1($parameters->team)) {
                        $code = ptb_stringify();
                    }
                    return [false, "Verification error"];
                }
            }
            return [false, "Structure error"];
        }
        return [false, "Parameters error"];
    }
    return [false, "Unknown action"];
}, true);

echo json_encode($result);

function ptb_parse($encoded)
{
    try {
        return json_decode(ptb_decode($encoded));
    } catch (Exception $e) {
        return null;
    }
}

function ptb_decode($str)
{
    if (strlen($str) === 0) return "";
    $s = 0;
    for ($i = 0; $i < strlen($str); $i++) {
        $c = ord($str[$i]);
        if ($c === "3") break;
        $s <<= 1;
        if ($c === "2") $s += 1;
    }
    return chr($s) . ptb_decode(substr($str, $i + 1));
}