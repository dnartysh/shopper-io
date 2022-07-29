function checkPassword() {
    let pass = document.getElementById('new-pass').value;
    let checkedPass = document.getElementById('new-pass-checked').value;

    return pass === checkedPass;
}

function checkPasswordAndFieldsFullness() {
    let pass = document.getElementById('new-pass').value;
    let checkedPass = document.getElementById('new-pass-checked').value;

    return pass === checkedPass && pass !== '' && checkedPass !== '';
}

function checkUsername() {
    let username = document.getElementById('username').value;

    return username !== '';
}

function checkFirstname() {
    let firstname = document.getElementById('firstname').value;

    return firstname !== '';
}

function checkLastname() {
    let lastname = document.getElementById('lastname').value;

    return lastname !== '';
}