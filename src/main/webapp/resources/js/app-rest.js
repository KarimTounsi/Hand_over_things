$(function () {
    let BASE_URL = 'http://localhost:8080';

    function ajax() {
        function GetAllInstitutions() {
            $('.tableInput').empty();
            $.ajax({
                url: BASE_URL + '/api/institutions/',
                method: $('.tableInput').data('method'),
                dataType: 'json'
            }).done(function (result) {
                result.forEach(function (element) {
                    let newTrElement = $('<tr>');
                    let newThElement = $('<th>');
                    let newTdElement1 = $('<td>');
                    let newTdElement2 = $('<td>');
                    let newTdElement3 = $('<td>');
                    let newTdElement4 = $('<td>');
                    let newTdElement5 = $('<td>');
                    newThElement.text(element.index);
                    newTdElement1.text(element.name);
                    newTdElement2.text(element.description);
                    newTdElement3.html("<button type=\"button\" class=\"btn btn-info editInstitution\">Edycja</button>")
                    newTdElement3.attr('id', element.id).data('method', 'PATCH');
                    newTdElement4.html("<button type=\"button\" class=\"btn btn-info fullEditInstitution\">Edycja całości</button>")
                    newTdElement4.attr('id', element.id).data('method', 'PUT');
                    newTdElement5.html("<button type=\"button\" class=\"btn btn-danger deleteInstitution\">Usuń</button>")
                    newTdElement5.attr('id', element.id).data('method', 'DELETE');
                    newTrElement.append(newThElement).append(newTdElement1).append(newTdElement2).append(newTdElement3).append(newTdElement4).append(newTdElement5);
                    // link.text('Usuń');
                    $('.tableInput').append(newTrElement);
                });
            });
        }
        GetAllInstitutions();

            function post() {
                $('#addButton').on('click', function (e) {
                    e.preventDefault();
                    let newInstitution = {
                        name: $('#name').val(),
                        description: $('#description').val(),
                        status: true,
                    };
                    $.ajax({
                        url: BASE_URL + "/api/institutions/",
                        method: $(this).data('method'),
                        data: JSON.stringify(newInstitution),
                        contentType: 'application/json'
                    }).done(function () {
                        GetAllInstitutions();
                        $('#name').val('');
                        $('#description').val('');


                    });
                });
            }

            post();

        function putUpdateInstitution() {
            $('.tableInput').on('click', $('.fullEditInstitution'), function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $(this).attr('id'),
                    name: $('#name').val(),
                    description: $('#description').val(),
                    status: true,
                };
                $.ajax({
                    url: BASE_URL + '/api/institutions/' + $(this).attr('id'),
                    method: $(this).data('method'),
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllInstitutions();
                    $('#name').val('');
                    $('#description').val('');
                });
            });
        }
        putUpdateInstitution();

        function patchUpdateInstitution() {
            $('.tableInput').on('click', $('.editInstitution'), function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $(this).attr('id'),
                    name: $('#name').val(),
                    description: $('#description').val(),
                    status: true,
                };
                $.ajax({
                    url: BASE_URL + '/api/institutions/' + $(this).attr('id'),
                    method: $(this).data('method'),
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllInstitutions();
                    $('#name').val('');
                    $('#description').val('');
                });
            });
        }
        patchUpdateInstitution();

            function deleteInstitution() {
                $('.tableInput').on('click', $('.deleteInstitution'), function (element) {
                    $.ajax({
                        url: BASE_URL + '/api/institutions/' + $(this).attr('id'),
                        method: $(this).data('method'),
                    }).done(function (result) {
                        GetAllInstitutions();
                    });
                });
            }

            deleteInstitution();
        }
    ajax();
});
