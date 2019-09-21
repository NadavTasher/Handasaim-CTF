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
 *     command: digitalWrite, analogWrite, servoWrite
 *     parameter: 0-180
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
                        $code = ptb_stringify($parsed);
                        $code = base64_encode($code);
                        return [true, "Code was sent to the scheduler.\nCompiled code: $code"];
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
        $c = $str[$i];
        if ($c === "3") break;
        $s <<= 1;
        if ($c === "2") $s += 1;
    }
    return chr($s) . ptb_decode(substr($str, $i + 1));
}

function ptb_stringify($code)
{
    $string = "";
    if (isset($code->codes)) {
        foreach ($code->codes as $command) {
            if (isset($command->selectpin) && isset($command->command) && isset($command->parameter) && isset($command->length)) {
                $w_command = ($command->command === "digitalWrite" ? 0 : ($command->command === "analogWrite" ? 1 : ($command->command === "servoWrite" ? 2 : -1)));
                $w_pin = intval($command->selectpin);
                $w_parameter = intval($command->parameter);
                $w_length = intval($command->length);
                if ($w_command >= 0 && $w_pin > 1 && $w_pin <= 12 && $w_parameter >= 0 && $w_parameter <= 180 && $w_length > 0 && $w_length <= 1000) {
                    $current = "";
                    $current .= chr(1 + $w_pin);
                    $current .= chr(1 + $w_command);
                    $current .= chr(1 + $w_parameter);
                    $current .= chr(1 + $w_length);
                    $current .= chr(0);
                    $string .= $current;
                }
            }
        }
    }
    return $string;
}