<?php

include_once __DIR__ . "/../base/api.php";

const HASH_MAP = [
    "dogepass" => "f06cda5c096c84e2cae334dc5e8d88309b1a259ee1585bc9663f1b9cd8180d00",
    "mysecret" => "03c718771cadea9bf9ed6e380ec2dcf61d21b311432a538f365ac459d84783ac",
    "lostpen" => ""
];

api("check", function ($action, $parameters) {
    if ($action === "check") {
        if (isset($parameters->challenge) && isset($parameters->flag)) {
            if ($parameters->challenge === "package") return [$parameters->flag === "fb9bbb861f1dbb8e3c66235293fef2736d583ff4e46a85f92566f78659f8ab14", ""];
            if ($parameters->challenge === "happycake") return [$parameters->flag === "9efbd50e82ec29217ca0d11b969f81e85770e2e89bb444f4313dc52150bc0abd", ""];
            return [false, ""];
        }
        return [false, ""];
    }
    return [false, ""];
}, true);

echo json_encode($result);