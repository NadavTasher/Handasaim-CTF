<?php

shell_exec("docker stop $(docker ps -aq)");