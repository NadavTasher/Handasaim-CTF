<?php
/**
 * This is the build-all file.
 * Add your build code in your section.
 */

// Nadav's run-code

const DOCKERABLES = [
//    "ctfd",
    "dogepass",
    "easyshaing",
    "catgallery",
    "chatm8",
    "safecrafter",
    "apppackager",
    "pushthebot",
//    "appletree"
];

foreach (DOCKERABLES as $path => $name) {
    $port = 80+intval($path);
    echo "[ ] Docker starting $name";
    shell_exec("docker run -p 80$port:80 -d --rm " . $name);
    echo "\r\033[K[X] Docker started $name\n";
}