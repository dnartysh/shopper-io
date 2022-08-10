var hostUrl = 'http://' + window.location.host;

function drawAdminCharts() {
    $(document).ready(function () {
        $.ajax({
            type : 'GET',
            url : hostUrl + '/api/chart/user',
            success : function(data) {
                var config = {
                    labels: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
                        "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                    series: [JSON.parse(data)]
                };

                var options = {
                    height: '550px',
                    chartPadding: {
                        top: 20,
                        left: 10,
                        right: 10,
                    },
                    low: 0,
                    lineSmooth: Chartist.Interpolation.simple({
                        tension: 1,
                        fillHoles: false
                    })
                };

                new Chartist.Line('#chart-user', config, options);
            },
            error : function () {
                console.log('Error load data in "/api/chart/user"');
            }
        });
    })
}

function drawStatisticCharts() {
    $(document).ready(function () {
        $.ajax({
            type : 'GET',
            url : hostUrl + '/api/chart/user/period',
            success : function(data) {
                var config = {
                    labels: ["Админ", "Продавец", "Менеджер", "Кладовщик"],
                    series: JSON.parse(data)
                }

                var options = {
                    height: '270px',
                    distributeSeries: true
                }

                new Chartist.Bar('#chart-user-by-position', config, options);
            },
            error : function () {
                console.log('Error load data in "/api/chart/user/period"');
            }
        });

        $.ajax({
            type : 'GET',
            url : hostUrl + '/api/chart/server/memory',
            success : function(data) {
                var sum = function(a, b) {
                    return a + b;
                };

                var config = {
                    series: [30, 70]
                }

                var options = {
                    height: '270px',
                    labelInterpolationFnc: function(value) {
                        return Math.round(value / config.series.reduce(sum) * 100) + '%';
                    }
                }

                new Chartist.Pie('#chart-memory-server', config, options);
            },
            error : function () {
                console.log('Error load data in "/api/chart/server/memory"');
            }
        });
    })
}