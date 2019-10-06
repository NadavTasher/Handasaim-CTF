<?php

const MESSAGE_DATABASE = __DIR__ . DIRECTORY_SEPARATOR;

$message_database = json_decode(file_get_contents(MESSAGE_DATABASE));

$string = "";

foreach ($message_database as $id => $message) $string .= $id;

$string = base64_encode($string);

echo $string;

http_response_code(404);