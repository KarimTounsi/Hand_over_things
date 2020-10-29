$(function () {
    let BASE_URL = 'http://localhost:8080';
    let URL = '/api/user/';
    function ajax() {
        function GetAllUsers() {
            $('.tableInput').empty();
            $.ajax({
                url: BASE_URL + URL,
                method: $('.tableInput').data('method'),
                dataType: 'json'
            }).done(function (result) {
                result.forEach(function (element) {
                    let newTrElement = $('<tr>');
                    let newThElement = $('<th>');
                    let newTdElement1 = $('<td>');
                    let newTdElement3 = $('<td>');
                    let newTdElement4 = $('<td>');
                    let newTdElement5 = $('<td>');
                    newThElement.text(element.id);
                    newTdElement1.text(element.email);
                    newTdElement3.html("<button type=\"button\" class=\"btn btn-info editInstitution\">Edycja</button>")
                    newTdElement3.attr('id', element.id).data('method', 'PATCH');
                    newTdElement4.html("<button type=\"button\" class=\"btn btn-info fullEditInstitution\">Edycja całości</button>")
                    newTdElement4.attr('id', element.id).data('method', 'PUT');

                    newTdElement5.html("<button type=\"button\" class=\"btn btn-danger deleteInstitution\">Usuń</button>")
                    newTdElement5.attr('id', element.id).data('method', 'DELETE');
                    newTrElement.append(newThElement).append(newTdElement1).append(newTdElement3).append(newTdElement4).append(newTdElement5);
                    // link.text('Usuń');
                    $('.tableInput').append(newTrElement);
                });
            });
        }

        GetAllUsers();


        function putUpdateUser() {
            $('.tableInput').on('click', '.fullEditInstitution', function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $(this).parent().attr('id'),
                    email: $('#email').val(),
                    password: $('#password').val(),
                };
                $.ajax({
                    url: BASE_URL + URL + $(this).parent().attr('id'),
                    method: $(this).parent().data('method'),
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllUsers();
                    $('#email').val('');
                    $('#password').val('');
                });
            });
        }

        putUpdateUser();

        function patchUpdateUser() {
            $('.tableInput').on('click', '.editInstitution', function (element) {
                // console.log($(this).parent())
                // console.log($(this))
                // console.log($(this).parent().attr('id'))
                element.preventDefault();
                let newInstitution = {
                    id: $(this).parent().attr('id'),
                    email: $('#email').val(),
                    password: $('#password').val(),
                };
                $.ajax({
                    url: BASE_URL + URL + $(this).parent().attr('id'),
                    method: $(this).parent().data('method'),
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    GetAllUsers();
                    $('#email').val('');
                    $('#password').val('');
                });
            });
        }
        patchUpdateUser();
        function deleteUser() {
            $('.tableInput').on('click', '.deleteInstitution', function (element) {
                $.ajax({
                    url: BASE_URL + URL + $(this).parent().attr('id'),
                    method: $(this).parent().data('method'),
                }).done(function (result) {
                    GetAllUsers();
                });
            });
        }
        deleteUser();

        function GetUser() {
            // $('.tableInput').empty();
            $.ajax({
                url: BASE_URL + URL + $('.id').data('id'),
                method: $('.id').data('method'),
                dataType: 'json'
            }).done(function (result) {
                $('#email').val(result.email)
            });
        }
        GetUser();

        function putUpdate() {
            $('#addButton').on('click', function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $('.id').data('id'),
                    email: $('#email').val(),
                    password: $('#password').val(),
                };
                $.ajax({
                    url: BASE_URL + URL + $('.id').data('id'),
                    method: 'PUT',
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    $('#email').val('');
                    $('#password').val('');
                });
            });
        }
        putUpdate();

    }
    ajax();
});
