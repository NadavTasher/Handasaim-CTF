const ENDPOINT = "scripts/backend/manager/manage.php";
const API = "manage";

function load() {
    view("home");
    if (!cookie_has("session")) {
        api(ENDPOINT, API, "session_welcome", {}, (success, result, error) => {
            if (success) {
                cookie_push("session", result[0]);
                get("hash").innerText = "Your thing is: " + result[1];
            }
        });
    } else {
        api(ENDPOINT, API, "session_load", {session: cookie_pull("session")}, (success, result, error) => {
            if (success) {
                get("hash").innerText = "Your thing is: " + result;
            }
        });
    }
}

function reset() {
    cookie_push("session", "");
}

function checkOnBackend() {
    api(ENDPOINT, API, "session_check", {session: cookie_pull("session"), word: get("in").value}, (success, result, error) => {
        if (success) {
            view("output");
            get("output").innerText = result;
            reset();
        } else {
            get("hash").innerText = error;
        }
    });
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