function clearUserFields() {
    document.getElementById('popup-header-text').innerHTML = 'Новый пользователь';

    document.getElementById('username').value = '';
    document.getElementById('firstname').value = '';
    document.getElementById('lastname').value = '';
    document.getElementById('birthdate').value = '';
    document.getElementById('active').checked = '';
    document.getElementById('new-user-checkbox').checked = true;
}

function fillUsersFields() {
    document.getElementById('popup-header-text').innerHTML = 'Редактирование пользователя';
    var table = document.getElementById('users-table');

    $('#users-table td').on('click',function() {
        var index = $(this).parent().index();

        document.getElementById('username').value = table.rows[index + 1].cells[1].innerHTML;
        document.getElementById('firstname').value = table.rows[index + 1].cells[2].innerHTML;
        document.getElementById('lastname').value = table.rows[index + 1].cells[3].innerHTML;
        document.getElementById('birthdate').value = table.rows[index + 1].cells[4].innerHTML;
        document.getElementById('active').checked = table.rows[index + 1].cells[6].innerHTML;
        document.getElementById('new-user-checkbox').checked = false;
    });
}