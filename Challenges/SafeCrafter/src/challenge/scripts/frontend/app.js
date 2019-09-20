function load() {
    view("home");
}

function craft() {
    let key = get("k").value;
    let data = get("d").value;
    download("Safe.1337", lock(key, data));
    view("home");
}

function pock() {
    view("content");
    get("content").innerText = unlock(get("u").value, get("s").value);
}

function lock(key, data) {
    let h = (key) => {
        let h = "";
        h += key.length;
        for (let i = 0; i < (key.length + 1) / 2; i++) {
            h += key[i].charCodeAt(0) + key[key.length - (1 + i)].charCodeAt(0);
        }
        if (key.length % 2 === 1) h += key[(key.length + 1) / 2].charCodeAt(0);
        return h;
    };
    let e = (key, data, last = 0) => {
        if (data === "") return "";
        let x = data[0].charCodeAt(0) + key[data.length % key.length].charCodeAt(0) + last;
        return String.fromCharCode(x) + e(key, data.substr(1), data[0].charCodeAt(0));
    };
    let j = function unlockSafe(key, safe, last = 0) {
        if (safe === "") return "";
        let x = safe[0].charCodeAt(0) - key[safe.length % key.length].charCodeAt(0) - last;
        return String.fromCharCode(x) + unlockSafe(key, safe.substr(1), x);
    };
    let safe = {
        hash: h(key),
        data: e(key, data),
        f: j.toString()
    };
    return encode(JSON.stringify(safe));
}

function unlock(key, safe) {
    let j = JSON.parse(decode(safe));
    eval(j.f);
    return unlockSafe(key, j.data);
}

function encode(str) {
    if (str.length === 0) return "";
    let c = str[0];
    let r = "";
    let cks = 0;
    c = c.charCodeAt(0);
    while (c > 0) {
        if ((c & 1) === 1) {
            r = "7" + r;
            cks++;
        } else {
            r = "8" + r;
            cks++;
            cks++;
        }
        c >>= 1;
    }
    return r + ["4", "5", "6"][Math.floor(magik(cks) * 3)] + encode(str.substr(1));
}

function decode(str) {
    if (str.length === 0) return "";
    let s = 0;
    let i;
    for (i = 0; i < str.length; i++) {
        let c = str[i];
        if (c === "4" || c === "5" || c === "6") break;
        s <<= 1;
        if (c === "7") s += 1;
    }
    return String.fromCharCode(s) + decode(str.substr(i + 1));
}

function magik(i) {
    let x = Math.sin(i) * 10000;
    return x - Math.floor(x);
}