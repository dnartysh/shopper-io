$(document).ready(function () {
    $.ajax({
        type : 'GET',
        url : 'api/chart/user',
        success : function(data) {
            new Chart(document.getElementById('new-users-chart'), JSON.parse(data));
        },
        error : function () {
            console.log('Error load data in "api/chart/user"');
        }
    });
})

