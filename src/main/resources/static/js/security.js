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
    let username = document.getElementById('user-name').value;

    return username !== '';
}

function checkFirstname() {
    let firstname = document.getElementById('user-firstname').value;

    return firstname !== '';
}

function checkLastname() {
    let lastname = document.getElementById('user-lastname').value;

    return lastname !== '';
}

function checkPosition() {
    let position = document.getElementById('user-position').value;

    return position !== '';
}

function checkName() {
    let  name = document.getElementById('product-name').value;

    return name !== '';
}