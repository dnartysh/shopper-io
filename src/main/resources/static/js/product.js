function clearProductFields() {
    document.getElementById('popup-header-text').innerHTML = 'Новый товар';

    document.getElementById('product-name').value = '';
    document.getElementById('product-price').value = '';
    document.getElementById('product-weight').value = '';
    document.getElementById('product-description').value = '';
}


function fillProductFields() {
    document.getElementById('popup-header-text').innerHTML = 'Редактирование товара';
    var table = document.getElementById('products-table');

    $('#products-table td').on('click',function() {
        var index = $(this).parent().index();

        document.getElementById('product-name').value = table.rows[index + 1].cells[1].innerHTML;
        document.getElementById('product-price').value = table.rows[index + 1].cells[2].innerHTML;
        document.getElementById('product-weight').value = table.rows[index + 1].cells[3].innerHTML;
        document.getElementById('product-description').value = table.rows[index + 1].cells[4].innerHTML;
    });
}