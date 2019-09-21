<?php

include_once __DIR__ . "/../base/api.php";

const PTB_SPEC = "";

api("pushthebot", function ($action, $parameters) {
    if ($action === "pushcode") {
        if (isset($parameters->code)) {
            $code = ptb_sanitize(ptb_parse($parameters->code));
            return [$code !== null, "Failed to parse or sanitize code"];
        }
    } else if ($action === "pullspec") {
        return [true, PTB_SPEC];
    }
    return [false, "Unknown action"];
}, true);

echo json_encode($result);