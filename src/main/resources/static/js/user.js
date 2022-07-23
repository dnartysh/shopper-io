function clearUserFields() {
    document.getElementById('popup-header-text').innerHTML = 'Новый пользователь';

    document.getElementById('user-username').value = '';
    document.getElementById('user-firstname').value = '';
    document.getElementById('user-lastname').value = '';
    document.getElementById('user-birthdate').value = '';
    document.getElementById('user-active').checked = '';
    document.getElementById('new-user-checkbox').checked = true;
}

function fillUsersFields() {
    document.getElementById('popup-header-text').innerHTML = 'Редактирование пользователя';
    var table = document.getElementById('users-table');

    $('#users-table td').on('click',function() {
        var index = $(this).parent().index();

        document.getElementById('user-username').value = table.rows[index + 1].cells[1].innerHTML;
        document.getElementById('user-firstname').value = table.rows[index + 1].cells[2].innerHTML;
        document.getElementById('user-lastname').value = table.rows[index + 1].cells[3].innerHTML;
        document.getElementById('user-birthdate').value = table.rows[index + 1].cells[4].innerHTML;
        document.getElementById('user-active').checked = table.rows[index + 1].cells[6].innerHTML;
        document.getElementById('new-user-checkbox').checked = false;
    });
}