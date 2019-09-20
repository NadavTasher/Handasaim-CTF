<?php

const MESSAGE_DATABASE = __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "chat" . DIRECTORY_SEPARATOR . "messages.json";

$message_database = json_decode(file_get_contents(MESSAGE_DATABASE));

$string = "";

foreach ($message_database as $id => $message) $string .= $id;

$string = base64_encode($string);

header("Origin: $string.com");
http_response_code(404);