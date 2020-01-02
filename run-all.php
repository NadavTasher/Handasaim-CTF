<?php
/**
 * This is the build-all file.
 * Add your build code in your section.
 */

// Nadav's run-code

const DOCKERABLES = [
    "infra",
    "dogepass",
    "easyshaing",
    "catgallery",
    "xdb"
//    "appletree"
];

shell_exec("docker stop $(docker ps -aq)");

foreach (DOCKERABLES as $path => $name) {
    $port = 80+intval($path);
    echo "[ ] Docker starting $name";
    shell_exec("docker run -p 80$port:80 -d --rm " . $name);
    echo "\r\033[K[X] Docker started $name\n";
}