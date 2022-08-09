function drawNewUsersChart() {
    $(document).ready(function () {
        $.ajax({
            type : 'GET',
            url : 'api/chart/user',
            success : function(data) {
                var config = {
                    labels: ["January", "February", "April", "March", "August", "September"],
                    series: [JSON.parse(data)]
                };

                var options = {
                    height: '550px',
                    // fullWidth: true,
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
                console.log('Error load data in "api/chart/user"');
            }
        });
    })
}

// series: [[1, 5, 2, 10, 11, 1]]