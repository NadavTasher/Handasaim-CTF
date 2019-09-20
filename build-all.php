<?php
/**
 * This is the build-all file.
 * Add your build code in your section.
 */


// Nadav's build-code

const DOCKERABLES = [
    "Infrastructure/CTFd" => "ctfd",
    "Challenges/DogePass" => "dogepass",
    "Challenges/EasyShaing" => "easyshaing",
    "Challenges/DogePass" => "dogepass",
    "Challenges/DogePass" => "dogepass",
    "Challenges/DogePass" => "dogepass",
    "Challenges/DogePass" => "dogepass",
    "Challenges/DogePass" => "dogepass"
];

foreach (DOCKERABLES as $path => $name) {
    echo "[ ] Docker building $name";
    shell_exec("cd " . (__DIR__ . "/" . $path) . " && " . ("docker build . -t " . $name));
    echo "\r[X] Docker built $name\n";
}

// Tal's build-code

// Alon's build-code

// Yarden's build-code
