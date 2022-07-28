function showSuccessPopup(message) {
    var check = document.getElementById('warning-popup');
    var bodyImg = document.getElementById('warning-popup-body-img');
    var body = document.getElementById('warning-popup-body-message');

    bodyImg.src = "/img/success.png";
    body.innerHTML = message;

    check.checked = true;
}

function showErrorPopup(message) {
    var check = document.getElementById('warning-popup');
    var bodyImg = document.getElementById('warning-popup-body-img');
    var body = document.getElementById('warning-popup-body-message');

    bodyImg.src = "/img/error.png";
    body.innerHTML = message;

    check.checked = true;
}