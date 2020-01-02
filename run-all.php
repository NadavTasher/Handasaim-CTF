<?php

shell_exec("docker stop $(docker ps -aq)");

echo "[ ] Docker starting infrastructure";
shell_exec("docker run -p 80:80 -p 443:443 -d --rm infrastructure");
echo "\r\033[K[X] Docker started infrastructure\n";

echo "[ ] Docker starting easyshaing";
shell_exec("docker run -p 8080:80 -d --rm easyshaing");
echo "\r\033[K[X] Docker started easyshaing\n";