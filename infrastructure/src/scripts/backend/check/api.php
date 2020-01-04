<?php

include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "base" . DIRECTORY_SEPARATOR . "api.php";
include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "leaderboard" . DIRECTORY_SEPARATOR . "api.php";

const HASH_MAP = [
    "dogepass" => "b4b5991a5f040e5ccbf88b89e3bde707151407bcbad82662ec5211bda0819edf",
    "mysecret" => "b8998a6b37e376037c49be950ddd5dff69c0f314778f96019e2d9e221b8a34b6",
    "lostpen" => "283700b85105847f7ef877b32f87d593329721984ca01a007c8f93074aace3c2",
    "easyshaing" => "0afe14441c3a8eda3b2ed914c2b3b0fbc9af949bdf5f0946c34fcd21889a6dfb",
    "happycooking" => "2953e5bd9092d56c65acf323437c843eb31b0f9ad3acbb64ee9db3a144104451"
];

function check()
{
    api("check", function ($action, $parameters) {
        if ($action === "check") {
            if (isset($parameters->challenge) && isset($parameters->flag)) {
                if (is_string($parameters->challenge) && is_string($parameters->flag)) {
                    if (array_key_exists($parameters->challenge, HASH_MAP)) {
                        $hashed = hash("sha256", $parameters->flag);
                        $solved = $hashed === HASH_MAP[$parameters->challenge];
                        if ($solved) {
                            if (isset($parameters->secret)) {
                                if (is_string($parameters->secret)) {
                                    user_mark($parameters->secret, $parameters->challenge);
                                }
                            }
                            return [true, "Challenge solved!"];
                        } else {
                            return [false, "Wrong flag :("];
                        }
                    }
                    return [false, "No such challenge"];
                }
            }
            return [false, "Parameter error"];
        }
        return [false, "Unknown action"];
    }, true);
}