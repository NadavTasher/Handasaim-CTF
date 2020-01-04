<!DOCTYPE html>
<!--
 Copyright (c) 2020 Kipod After Free
 https://github.com/KipodAfterFree/WebTemplate/
-->
<html row lang="en">
<head>
    <!-- START NOT CTF IMPORTANT -->
    <noscript></noscript>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=5, user-scalable=yes, minimal-ui"/>
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="theme-color" content="#FFFFFF"/>
    <link rel="icon" href="images/icon.png"/>
    <link rel="stylesheet" href="stylesheets/theme.css"/>
    <script type="text/javascript" src="scripts/frontend/base.js"></script>
    <!-- END NOT CTF IMPORTANT -->
    <link rel="stylesheet" href="stylesheets/app.css"/>
    <script type="text/javascript" src="scripts/frontend/app.js"></script>
    <title>AppName</title>
</head>
<body onload="load()" column>
<?php

const MAP = __DIR__ . DIRECTORY_SEPARATOR . "files" . DIRECTORY_SEPARATOR . "map.json";

function search($name, $map, $depth = 0)
{
    if (strlen($name) === 0 &&
        isset($map->recipe)) {
        return [true, $map->recipe];
    }
    if (isset($map->{$name[0]})) {
        return search(substr($name, 1), $map->{$name[0]}, $depth + 1);
    }
    return [false, "Failed after $depth characters"];
}

$recipe = null;
if (isset($_GET["recipe"])) {
    if (is_string($_GET["recipe"])) {
        $recipe = $_GET["recipe"];
    }
}
?>
<?php
if ($recipe === null) {
    echo "<p>Welcome to my recipe book!</p>\n";
    echo "<p>Here are some of my favorite recipes:</p>\n";
    echo "<button onclick='window.location = \"?recipe=Hummus\"'>Hummus</button>\n";
    echo "<button onclick='window.location = \"?recipe=Pizza\"'>Pizza</button>\n";
    echo "<button onclick='window.location = \"?recipe=Hamburger\"'>Hamburger</button>\n";
    echo "<button onclick='window.location = \"?recipe=Cupcakes\"'>Cupcakes</button>\n";
    echo "<button onclick='window.location = \"?recipe=Cookies\"'>Cookies</button>\n";
} else {
    $searched = search($recipe, json_decode(file_get_contents(MAP)));
    if ($searched[0]) {

    } else {

    }
}
?>

</body>
</html>