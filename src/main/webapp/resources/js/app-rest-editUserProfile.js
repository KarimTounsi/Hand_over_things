$(function () {
    let BASE_URL = 'http://localhost:8080';
    let URL = '/user/api/edit-profile/';

    function ajax() {
        function  GetUser() {
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


        function putUpdateEmail() {
            $('#addButton').on('click', function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $('.id').data('id'),
                    email: $('#email').val(),
                };
                $.ajax({
                    url: BASE_URL + URL +'email/'+ $('.id').data('id'),
                    method: 'PUT',
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    $('#email').val('');
                });
            });
        }
        putUpdateEmail();



        function putUpdatePassword() {
            $('#addButton2').on('click', function (element) {
                element.preventDefault();
                let newInstitution = {
                    id: $('.id').data('id'),
                    password: $('#password').val(),
                    passwordCheck: $('#passwordCheck').val(),
                };
                $.ajax({
                    url: BASE_URL + URL +'password/'+ $('.id').data('id'),
                    method: 'PUT',
                    data: JSON.stringify(newInstitution),
                    contentType: 'application/json'
                }).done(function () {
                    $('#password').val('');
                    $('#passwordCheck').val('');
                });
            });
        }
        putUpdatePassword();

    }

    ajax();
});
