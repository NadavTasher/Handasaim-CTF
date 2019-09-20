<?php

const WORDS_FILE_PATH = __DIR__ . "/../../../files/passwords.txt";
const DATABASE_FILE_PATH = __DIR__ . "/../../../files/database.json";

$database = json_decode(file_get_contents(DATABASE_FILE_PATH));

api("manage", function ($action, $parameters) {
    global $database;
    if ($action === "session_welcome") {
        $id = random(256);
        $word = sha1(select_word());
        $database->$id = $word;
        file_put_contents(DATABASE_FILE_PATH, json_encode($database));
        return [true, [$id, $word]];
    } else if ($action === "session_load") {
        if (isset($parameters->session)) {
            if (isset($database->{$parameters->session})) {
                return [true, $database->{$parameters->session}];
            } else {
                return [false, "No such session"];
            }
        } else {
            return [false, "Missing parameters"];
        }
    } else if ($action === "check") {
        if (isset($parameters->session) && isset($parameters->word)) {
            if (sha1($parameters->word) === $database->{$parameters->session}) {
                unset($database->{$parameters->session});
                file_put_contents(DATABASE_FILE_PATH, json_encode($database));
                return [true, "NAT{t00_3asy_4or_me3_2789546347}"];
            }else{
                return [false, "Wrong answer"];
            }
        } else {
            return [false, "Missing parameters"];
        }
    }
    return [false, "Unknown action"];
}, false);

echo json_encode($result);

function select_word()
{
    $words = explode("\n", file_get_contents(WORDS_FILE_PATH));
    shuffle($words);
    return $words[0];
}