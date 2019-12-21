<?php

include_once __DIR__ . "/../base/api.php";

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