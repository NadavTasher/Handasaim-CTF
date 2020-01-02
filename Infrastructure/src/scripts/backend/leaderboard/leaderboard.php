<?php

include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "base" . DIRECTORY_SEPARATOR . "api.php";

const SERVER_SECRET = "ca0kxxfu78dngbktux6c1phoore1kficjdvdz57bvcsi15559q7h9am4p5fue07pghys3twmk1550bf4z0zog8jsr1xvfvzvfh4dfk9k9y3z4q8tu5ilsf4fl1iyv7r0";

const USERS_DIRECTORY = __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "leaderboard";

function leaderboard()
{
    api("leaderboard", function ($action, $parameters) {
        if ($action === "list") {
            $users = array();
            $files = scandir(USERS_DIRECTORY);
            foreach ($files as $file) {
                if ($file[0] !== ".") {
                    $user = json_decode(file_get_contents(USERS_DIRECTORY . DIRECTORY_SEPARATOR . $file));
                    $object = new stdClass();
                    $object->name = $user->name;
                    $object->solves = count($object->solves);
                    array_push($users, $object);
                }
            }
            return [true, $users];
        } else if ($action === "register") {
            if (isset($parameters->name)) {
                if (is_string($parameters->name)) {
                    if (strlen($parameters->name) >= 2) {
                        $secret = user_register($parameters->name);
                        if ($secret !== null) {
                            return [true, $secret];
                        } else {
                            return [false, "User already registered"];
                        }
                    } else {
                        return [false, "Name too short"];
                    }
                }
            }
            return [false, "Parameter error"];
        }
        return [false, "Unknown action"];
    }, true);
}

function user_register($name)
{
    $hashed = user_hash($name);
    if (!file_exists(user_file($hashed))) {
        $user = new stdClass();
        $user->name = $name;
        $user->solved = array();
        file_put_contents(user_file($hashed), json_encode($user));
        return $hashed;
    }
    return null;
}

function user_mark($hash, $challenge)
{
    if (file_exists(user_file($hash))) {
        $array = json_decode(file_get_contents(user_file($hash)))->solves;
        if (!in_array($challenge, $array)) {
            array_push($array, $challenge);
            file_put_contents(user_file($hash), json_encode($array));
        }
    }
}

function user_hash($name)
{
    return hash_hmac("sha256", $name, SERVER_SECRET);
}

function user_file($hash)
{
    return USERS_DIRECTORY . DIRECTORY_SEPARATOR . $hash . ".json";
}