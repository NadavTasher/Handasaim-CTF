function load() {
    view("home");
}

function check(challenge) {
    api("scripts/backend/check/check.php", "check", "check", {
        challenge: challenge,
        flag: CryptoJS.SHA256(get(challenge + "-flag").value).toString(CryptoJS.enc.Hex)
    }, (success, result) => {
        if (success) {
            popup("Correct Flag!", 6000, "#88AA88");
        } else {
            popup("Incorrect Flag!", 6000, "#AA8888");
        }
    });
}

function open_link(link) {
    window.open(link, '_blank');
}