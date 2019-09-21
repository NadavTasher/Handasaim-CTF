const BLINK_CODE = "";

function load() {
    if (cookie_has("team")) get("team").value = cookie_pull("team");
    if (cookie_has("code") && cookie_pull("code") === "true") {
        show("code-entry");
        hide("blink");
    } else {
        show("blink");
        hide("code-entry");
    }
}

function cookie_pull(name) {
    name += "=";
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) === 0) {
            return decodeURIComponent(cookie.substring(name.length, cookie.length));
        }
    }
    return undefined;
}

function cookie_push(name, value) {
    const date = new Date();
    date.setTime(value !== "" ? date.getTime() + (365 * 24 * 60 * 60 * 1000) : 0);
    document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + date.toUTCString() + ";domain=" + window.location.hostname + ";path=/";
}

function cookie_has(name) {
    return cookie_pull(name) !== undefined;
}

function send(compiled) {
    if (get("team").value.length > 0) {
        api("scripts/backend/pushthebot/pushthebot.php", "pushthebot", "pushcode", {
            code: compiled,
            team: get("team").value
        }, (success, result, error) => {
            console.log("---------------------------------------");
            if (success) {
                get("output").style.color = "green";
                get("output").innerText = result;
            } else {
                get("output").style.color = "red";
                get("output").innerText = error;
            }
        });
    } else {
        get("team").style.position = "relative";
        animate("team", "left", ["0px", "-20px", "0px", "20px", "0px", "-20px", "0px", "20px", "0px"], 0.1);
    }
}

function compile(rawcode) {
    return rawcode;
}

function encode(str) {
    if (str.length === 0) return "";
    let c = str[0];
    let r = "";
    let cks = 0;
    c = c.charCodeAt(0);
    while (c > 0) {
        if ((c & 1) === 1) {
            r = "2" + r;
            cks++;
        } else {
            r = "1" + r;
            cks++;
            cks++;
        }
        c >>= 1;
    }
    return r + "3" + encode(str.substr(1));
}

function decode(str) {
    if (str.length === 0) return "";
    let s = 0;
    let i;
    for (i = 0; i < str.length; i++) {
        let c = str[i];
        if (c === "3") break;
        s <<= 1;
        if (c === "2") s += 1;
    }
    return String.fromCharCode(s) + decode(str.substr(i + 1));
}