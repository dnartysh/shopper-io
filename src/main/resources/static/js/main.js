const positionCookie = getCookie("position");

if (positionCookie === "admin") {
    var menus = new Vue({
        el: '#nav-list',
        data: {
            menus: [{name: 'Пользователи', url: '/admin/user'},
                {name: 'Статистика', url: '/admin/statistic'}]
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
            menus: [{name: 'Главная', url: '/newcomer'}]
        }
    })
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function tableSearch(tableId) {
    var phrase = document.getElementById('search-text');
    var table = document.getElementById(tableId);
    var regPhrase = new RegExp(phrase.value, 'i');
    var flag = false;

    for (var i = 1; i < table.rows.length; i++) {
        flag = false;

        for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
            flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
            if (flag) break;
        }

        if (flag) {
            table.rows[i].style.display = "";
        } else {
            table.rows[i].style.display = "none";
        }
    }
}

function submitData(elementId) {
    document.getElementById(elementId).submit();
}