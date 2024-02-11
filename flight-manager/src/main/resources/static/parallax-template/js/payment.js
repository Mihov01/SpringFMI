function validateForm() {
    var cardNumber = document.getElementById("cardNumber").value;
    var expiryDate = document.getElementById("expiryDate").value;
    var cvv = document.getElementById("cvv").value;

    var cardNumberRegex = /^\d{16}$/;
    var expiryDateRegex = /^\d{2}\/\d{2}$/;
    var cvvRegex = /^\d{3}$/;

    var isValid = true;

    if (!cardNumberRegex.test(cardNumber)) {
        document.getElementById("cardNumberError").innerHTML = "Please enter a valid 16-digit card number";
        isValid = false;
    } else {
        document.getElementById("cardNumberError").innerHTML = "";
    }

    if (!expiryDateRegex.test(expiryDate)) {
        document.getElementById("expiryDateError").innerHTML = "Please enter a valid expiry date (MM/YY)";
        isValid = false;
    } else {
        document.getElementById("expiryDateError").innerHTML = "";
    }

    if (!cvvRegex.test(cvv)) {
        document.getElementById("cvvError").innerHTML = "Please enter a valid 3-digit CVV";
        isValid = false;
    } else {
        document.getElementById("cvvError").innerHTML = "";
    }

    return isValid;
}