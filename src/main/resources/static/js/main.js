var currentUrl = document.URL
var hostURI = window.location.protocol + "//" + window.location.host;
var adminURI = hostURI + '/admin';
var userURI = hostURI + '/user';

const positionCookie = getCookie("position");
if (positionCookie === "admin") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Пользователи', url: adminURI + '/user'},
                {name: 'Статистика', url: adminURI + '/statistic'}]
        }
    })
} else if (positionCookie === "seller") {
    var menus = new Vue({
        el: '#nav-list',
        menus: [{name: 'Магазин', url: currentUrl + '/shop'},
            {name: 'Продажа', url: currentUrl + '/sell'}]
    })
} else if (positionCookie === "storekeeper") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Склад', url: currentUrl + '/storage'},
                {name: 'Поступления', url: currentUrl + '/entry'},
                {name: 'Отгрузки', url: currentUrl + '/dispatch'}]
        }
    })
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
