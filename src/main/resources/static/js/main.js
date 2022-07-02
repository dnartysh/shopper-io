// var currentUrl = document.URL
// var hostURI = window.location.protocol + "//" + window.location.host;
// var adminURI = hostURI + '/admin';
// var userURI = hostURI + '/user';

const positionCookie = getCookie("position");
if (positionCookie === "admin") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Пользователи', url: '/admin/user'},
                {name: 'Статистика', url: '/admin/statistic'},
                {name: 'Магазины', url: '/admin/shop'},
                {name: 'Склады', url: '/admin/storage'},]
        }
    })
} else if (positionCookie === "seller") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
        menus: [{name: 'Магазины', url: '/seller/shop'},
            {name: 'Продажи', url: '/seller/sell'}]
        }
    })
} else if (positionCookie === "storekeeper") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Склады', url: '/storekeeper/storage'},
                {name: 'Поступления', url: '/storekeeper/entry'},
                {name: 'Отгрузки', url: '/storekeeper/dispatch'}]
        }
    })
} else if (positionCookie === "manager") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Товары', url: '/manager/product'},
                {name: 'Заказы', url: '/manager/order'},
                {name: 'Магазины', url: '/manager/shop'}]
        }
    })
} else if (positionCookie === "newcomer") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Главная', url: '/'}]
        }
    })
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}