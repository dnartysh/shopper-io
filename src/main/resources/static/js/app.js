new Vue({
    el: '#nav-list',
    data: {
        storekeeperMenu: [{name: 'Склад', url: '/storage'},
            {name: 'Поступления', url: '/entry'},
            {name: 'Отгрузки', url: '/dispatch'}],
        sellerMenu: [{name: 'Магазин', url: '/shop'},
            {name: 'Продажа', url: '/sell'}],
        adminMenu: [{name: 'Пользователи', url: '/user'},
            {name: 'Статистика', url: '/statistic'}]
    }
})