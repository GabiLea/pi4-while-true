function FinalizarCompras() {
    var arr = [];
    $("#body-carrinho tr").each(function () {
        var linha = $(this);
        var qtd = linha.find('.quantidade').val();
        var produtoID = linha.attr('produtoid');

        arr.push({id: produtoID, quantidade: qtd});
    });

    $.ajax({
        url: '/loja/finalizar-compra',
        type: 'post',
        dataType: "json",
        data: {strCarrinho: JSON.stringify(arr)},
        success: function (result) {
            debugger;
//            location.href = '/';
        },
        error: function (xhr, status, error) {
            debugger;
            console.log(error);
        }
    });

}

$(document).ready(function () {
    $('#finalizar').click(function (e) {
        FinalizarCompras();
    });

//    $.ajax({url: "/loja/finalizar-compra",
//     type: "post", dataType: "application/json", 
//     data: {strJson: JSON.stringify(arr)}, 
//     success: function (response) {
//            window.location = response;
//        }});


});
