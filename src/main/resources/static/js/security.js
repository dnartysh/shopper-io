function checkPassword() {
    var pass = document.getElementById('new-pass').value;
    var checkedPass = document.getElementById('new-pass-checked').value;

    return pass === checkedPass;
}