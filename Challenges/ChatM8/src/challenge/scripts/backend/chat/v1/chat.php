<?php

include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "base" . DIRECTORY_SEPARATOR . "api.php";
include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "accounts" . DIRECTORY_SEPARATOR . "api.php";

const CHAT_DATABASE = __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "chat" . DIRECTORY_SEPARATOR . "chats.json";
const MESSAGE_DATABASE = __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "chat" . DIRECTORY_SEPARATOR . "messages.json";

$chat_database = json_decode(file_get_contents(CHAT_DATABASE));
$message_database = json_decode(file_get_contents(MESSAGE_DATABASE));

api("chat", function ($action, $parameters) {
    $user = accounts();
    if ($user !== null) {
        if ($action === "list") {
            return [true, chat_list($user->id)];
        } else if ($action === "read") {
            if (isset($parameters->user)) {
                return [true, chat_read($user->id, $parameters->user)];
            }
            return [false, "Missing parameters"];
        } else if ($action === "write") {
            if (isset($parameters->user) && isset($parameters->content)) {
                if (accounts_user($parameters->user) !== null) {
                    chat_write($user->id, $parameters->user, $parameters->content);
                    chat_write($parameters->user, $user->id, $parameters->content);
                    return [true, "Sent"];
                }
                return [false, "No destination"];
            }
            return [false, "Missing parameters"];
        }
    } else {
        return [false, "Authentication failure"];
    }
    return [false, "Error"];
}, true);

echo json_encode($result);

function chat_list($id)
{
    global $chat_database;
    $chats = array();
    if (isset($chat_database->$id)) {
        foreach ($chat_database->$id as $user => $chat) {
            array_push($chats, $user);
        }
    }
    return $chats;
}

function chat_read($user1, $user2)
{
    global $chat_database;
    if (isset($chat_database->$user1)) {
        if (isset($chat_database->$user1->$user2)) {
            return $chat_database->$user1->$user2;
        }
    }
    return array();
}

function chat_write($user1, $user2, $content)
{
    global $chat_database;
    if (!isset($chat_database->$user1)) $chat_database->$user1 = new stdClass();
    if (!isset($chat_database->$user1->$user2)) $chat_database->$user1->$user2 = array();
    array_push($chat_database->$user1->$user2, $content);
    file_put_contents(CHAT_DATABASE, json_encode($chat_database));
}