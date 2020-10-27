$(function () {
    let BASE_URL = 'http://localhost:8080';
    let URL = '/user/api/donations/';

    function ajax() {
        function GetAllDonations() {
            $('.list').empty();
            $.ajax({
                url: BASE_URL + URL,
                method: $('.list').data('method'),
                dataType: 'json'
            }).done(function (result) {
                result.forEach(function (element) {
                    let newLiElement = $('<li>');
                    let newDivElement = $('<div>');
                    let newDivElement1 = $('<div>');
                    let newDivElement2 = $('<div>');
                    let newDivElement3 = $('<div>');

                    let status = 'Nie zrealizowane';
                    if(element.receivingStatus === false){
                        status = 'Nie zrealizowane'
                    } else if (element.receivingStatus === true){
                        status = 'zrealizowane'
                    }
                    newLiElement.attr('id', element.id);
                    newDivElement.attr('class','col')
                    newDivElement1.attr('class','title').text(element.created);
                    newDivElement2.attr('class','subtitle').text(status);
                    newDivElement3.attr('class','subtitle').text(element.pickUp);
                    newDivElement.append(newDivElement1).append(newDivElement2).append(newDivElement3);
                    newLiElement.append(newDivElement);
                    $('.list').append(newLiElement);
                });
            });
        }

        GetAllDonations();

        function post() {
            $('#addButton').on('click', function (e) {
                e.preventDefault();
                let newInstitution = {
                    email: $('#email').val(),
                    password: $('#password').val(),
                };
                $.ajax({
                    url: BASE_URL + URL,
                    method: $(this).data('method'),
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllAdmins();
                    $('#email').val('');
                    $('#password').val('');


                });
            });
        }

    }

    ajax();
});
