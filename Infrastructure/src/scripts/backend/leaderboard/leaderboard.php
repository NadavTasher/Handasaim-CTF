<?php

include_once __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "base" . DIRECTORY_SEPARATOR . "api.php";

const SERVER_SECRET = "ca0kxxfu78dngbktux6c1phoore1kficjdvdz57bvcsi15559q7h9am4p5fue07pghys3twmk1550bf4z0zog8jsr1xvfvzvfh4dfk9k9y3z4q8tu5ilsf4fl1iyv7r0";

const USERS_DIRECTORY = __DIR__ . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . ".." . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "leaderboard";

function leaderboard()
{

}

function user_register($name)
{
    $hashed = user_hash($name);
    if (!file_exists(user_file($hashed))) {
        file_put_contents(user_file($hashed), json_encode(array()));
        return $hashed;
    }
    return null;
}

function user_mark($hash, $challenge)
{
    if (file_exists(user_file($hash))) {
        $array = json_decode(file_get_contents(user_file($hash)));
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