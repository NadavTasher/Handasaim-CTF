<?php

$map = new stdClass();

while (true) {
    $name = readline("Name of recipe: ");
    if ($name === "exit")
        break;
    $recipe = readline("Recipe: ");
    $temp = $map;
    while (strlen($name) !== 0) {
        if (!isset($temp->{$name[0]})) {
            $temp->{$name[0]} = new stdClass();
        }
        $temp = $temp->{$name[0]};
        $name = substr($name, 1);
    }
    $temp->recipe = $recipe;
}

file_put_contents("map.json", json_encode($map));