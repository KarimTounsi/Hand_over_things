$(function () {
    let BASE_URL = 'http://localhost:8080';
    // let BASE_URL = 'https://charity-web-service.herokuapp.com';
    let URL = '/api/donations/';

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
                    let newDivElement4 = $('<div>');
                    let newDivElement5 = $('<div>');
                    let newDivElement6 = $('<div>');

                    let newDivElement7;

                    let status = 'Nie zrealizowane';
                    if (element.receiveStatus === false) {
                        status = 'Nie zrealizowane'
                        newDivElement7 = $('<div>');
                        newDivElement7.attr('class', 'h3 confirm text-success').text('Potwierdź odbiór').attr('id',element.id).data('method','PUT');
                    } else if (element.receiveStatus === true) {
                        status = 'zrealizowane'
                    }
                    let list = element.categories
                    let result='Dary: ';
                    for (let i = 0; i < list.length; i++) {
                        result += list[i].name + '. '
                    }

                    let bagsName = "";
                    if (element.quantity === 1) {
                        bagsName = "worek";
                    } else if (element.quantity > 1 && element.quantity < 5 || element.quantity > 31 && element.quantity < 34) {
                        bagsName = "worki";
                    } else {
                        bagsName = "worków";
                    }

                    newLiElement.attr('id', element.id);
                    newDivElement.attr('class', 'col')
                    newDivElement1.attr('class', 'title').text('Dla: ' + element.institution.name);
                    newDivElement2.attr('class', 'subtitle').text(element.created);
                    newDivElement3.attr('class', 'subtitle').text(result)
                    newDivElement4.attr('class', 'subtitle').text(element.quantity + ' '+ bagsName);
                    newDivElement5.attr('class', 'subtitle').text(status);
                    newDivElement6.attr('class', 'subtitle').text(element.pickUp);
                    newDivElement.append(newDivElement1).append(newDivElement2).append(newDivElement3)
                        .append(newDivElement4).append(newDivElement5).append(newDivElement6).append(newDivElement7);
                    newLiElement.append(newDivElement);
                    $('.list').append(newLiElement);
                });
            });
        }
        GetAllDonations();

        function GetAllDonationsSorted() {
            $('.sort').on('click', function (e) {
                e.preventDefault();
                $('.list').empty();
                $.ajax({
                    url: BASE_URL + URL + $(this).data('path'),
                    method: $('.list').data('method'),
                    dataType: 'json'
                }).done(function (result) {
                    result.forEach(function (element) {
                        let newLiElement = $('<li>');
                        let newDivElement = $('<div>');
                        let newDivElement1 = $('<div>');
                        let newDivElement2 = $('<div>');
                        let newDivElement3 = $('<div>');
                        let newDivElement4 = $('<div>');
                        let newDivElement5 = $('<div>');
                        let newDivElement6 = $('<div>');

                        let newDivElement7;

                        let status = 'Nie zrealizowane';
                        if (element.receiveStatus === false) {
                            status = 'Nie zrealizowane'
                            newDivElement7 = $('<div>');
                            newDivElement7.attr('class', 'h3 confirm text-success').text('Potwierdź odbiór').attr('id',element.id).data('method','PUT');
                        } else if (element.receiveStatus === true) {
                            status = 'zrealizowane'
                        }
                        let list = element.categories
                        let result='Dary: ';
                        for (let i = 0; i < list.length; i++) {
                            result += list[i].name + '. '
                        }


                        let bagsName = "";
                        if (element.quantity === 1) {
                            bagsName = "worek";
                        } else if (element.quantity > 1 && element.quantity < 5 || element.quantity > 31 && element.quantity < 34) {
                            bagsName = "worki";
                        } else {
                            bagsName = "worków";
                        }

                        newLiElement.attr('id', element.id);
                        newDivElement.attr('class', 'col')
                        newDivElement1.attr('class', 'title').text('Dla: ' + element.institution.name);
                        newDivElement2.attr('class', 'subtitle').text(element.created);
                        newDivElement3.attr('class', 'subtitle').text(result)
                        newDivElement4.attr('class', 'subtitle').text(element.quantity + ' '+ bagsName);
                        newDivElement5.attr('class', 'subtitle').text(status);
                        newDivElement6.attr('class', 'subtitle').text(element.pickUp);
                        newDivElement.append(newDivElement1).append(newDivElement2).append(newDivElement3)
                            .append(newDivElement4).append(newDivElement5).append(newDivElement6).append(newDivElement7);
                        newLiElement.append(newDivElement);
                        $('.list').append(newLiElement);
                    });
                });
            });
    }
        GetAllDonationsSorted();

        function putUpdateDonation() {
            $('.list').on('click', '.confirm', function (element) {
                element.preventDefault();
                let newDonation = {
                    id: $(this).attr('id'),
                };
                $.ajax({
                    url: BASE_URL + URL + $(this).attr('id'),
                    method: $(this).data('method'),
                    data: JSON.stringify(newDonation),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllDonations();
                });
            });
        }

        putUpdateDonation();

}
    ajax();
});
