let BASE_URL = 'http://localhost:8080';
let URL = '/api/reset/';

function putResetPasswordEmailInput() {
    $('#addEmailButton').on('click', function (element) {
        element.preventDefault();
        let userEmail = {
            email: $('#email').val(),
        };
        $.ajax({
            url: BASE_URL + URL,
            method: 'PUT',
            data: JSON.stringify(userEmail),
            contentType: 'application/json'
        }).fail(function (xhr, status, error) {
            // let err = JSON.parse(xhr.responseText);
            // alert(err.message + xhr.responseText)
        }).done(function () {
            $('#email').val('');
        });
    });
}
    function putResetPassword() {
        $('#addPasswordButton').on('click', function (element) {
            element.preventDefault();
            let userPassword = {
                password: $('#password').val(),
                passwordCheck: $('#passwordCheck').val(),
                id: $('#id').val(),
                token: $('#token').val()
            };
            $.ajax({
                url: BASE_URL + URL+'/resetPassword',
                method: 'PUT',
                data: JSON.stringify(userPassword),
                contentType: 'application/json'
            }).fail(function (xhr, status, error) {
                // let err = JSON.parse(xhr.responseText);
                // alert(err.message + xhr.responseText)
            }).done(function () {
                $('#password').val('');
                $('#passwordCheck').val('');
            });
        });

    }
