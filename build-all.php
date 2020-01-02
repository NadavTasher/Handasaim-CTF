<?php

const DOCKERABLES = [
    "Infrastructure" => "infrastructure",
    "Challenges/EasyShaing" => "easyshaing",
];

foreach (DOCKERABLES as $path => $name) {
    echo "[ ] Docker building $name";
    shell_exec("cd " . (__DIR__ . "/" . $path) . " && " . ("docker build . -t " . $name));
    echo "\r\e[K[X] Docker built $name\n";
}