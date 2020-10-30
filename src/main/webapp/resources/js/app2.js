$(function () {

    let checkboxCategory = $('.ch-category')
    checkboxCategory.attr("status", false);
    checkboxCategory.on('click', function () {
        if ($(this).attr("status") === "false") {
            $(this).attr("status", true);
        } else {
            $(this).attr("status", false);
        }
    });

    let checkboxInstitutions = $('.radio')
    checkboxInstitutions.attr("status", false);
    checkboxInstitutions.on('click', function () {
        checkboxInstitutions.attr("status", false);
        if ($(this).attr("status") === "false") {
            $(this).attr("status", true);
        }

    });


    let lastButton = $('.lastButton');

    lastButton.on('click', function () {


        let quantityOfBags = $('.bags').val();



        let boxesChecked1 = $('.ch-category[status=true]')

        let categories = "";
        for (let i = 0; i < boxesChecked1.siblings('span').length; i++) {

            categories += boxesChecked1.siblings('span')[i].innerText + ". ";

        }

        let boxesChecked2 = $('.radio[status=true]')
        let firstChild = $(boxesChecked2.next().children()[0])



        let streetInput = $("#street")
        let cityInput = $("#city")
        let zipCodeInput = $("#zipCode")
        let dateInput = $("#pickUpDate")
        let pickUpTimeInput = $("#pickUpTime")
        let pickUpCommentInput = $("#pickUpComment")
        let phoneNumberInput = $("#phoneNumber")

        let quantityCategory = $('.quantityCategory')
        let bagsName = "";
        if (quantityOfBags === 1) {
            bagsName = "worek";
        } else if (quantityOfBags > 1 && quantityOfBags < 5 || quantityOfBags > 31 && quantityOfBags < 34) {
            bagsName = "worki";
        } else {
            bagsName = "worków";
        }

        quantityCategory.text('');
        quantityCategory.append(quantityOfBags + " " + bagsName + " " + categories)

        let institution = $('.institutionData')

        institution.text('');
        institution.append("Dla " + firstChild.text())


        let street = $('.street');
        street.text('');
        street.append(streetInput.val());

        let city = $('.city');
        city.text('');
        city.append(cityInput.val())

        let zipCode = $('.zipCode');
        zipCode.text('');
        zipCode.append(zipCodeInput.val());


        let date = $('.date');
        date.text('');
        date.append(dateInput.val());

        let pickUpTime = $('.pickUpTime');
        pickUpTime.text('');
        pickUpTime.append(pickUpTimeInput.val());

        let pickUpComment = $('.pickUpComment');
        pickUpComment.text('');
        pickUpComment.append(pickUpCommentInput.val());

        let phoneNumber = $('.phoneNumber');
        phoneNumber.text('');
        phoneNumber.append(phoneNumberInput.val())

    });


});