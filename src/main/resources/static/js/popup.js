function showSuccessPopup(headerMessage, bodyMessage) {
    var check = document.getElementById('warning-popup');
    var header = document.getElementById('warning-popup-header');
    var bodyImg = document.getElementById('warning-popup-body-img');
    var body = document.getElementById('warning-popup-body');

    bodyImg.src = "/img/success.png";
    header.innerHTML = headerMessage;
    body.innerHTML = bodyMessage;

    check.checked = true;
}

function showErrorPopup(headerMessage, bodyMessage) {
    var check = document.getElementById('warning-popup');
    var header = document.getElementById('warning-popup-header');
    var bodyImg = document.getElementById('warning-popup-body-img');
    var body = document.getElementById('warning-popup-body');

    bodyImg.src = "/img/error.png";
    header.innerHTML = headerMessage;
    body.innerHTML = bodyMessage;

    check.checked = true;
}