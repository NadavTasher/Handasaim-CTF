function load() {
    view("home");
}

function check(challenge) {
    api("scripts/backend/check/check.php", "check", "check", {
        challenge: challenge,
        flag: CryptoJS.SHA256(get(challenge + "-flag").value).toString(CryptoJS.enc.Hex)
    }, (success, result) => {
        popup(result, 6000, success ? "#88AA88" : "#AA8888");
    });
}

function open_link(link) {
    window.open(link, '_blank');
}