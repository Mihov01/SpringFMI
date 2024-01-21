$(document).ready(function() {
    $("#registerForm").submit(function(event) {
        event.preventDefault();

        var username = $("#username").val();
        var password = $("#password").val();

        var userData = {
            username: username,
            password: password
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/auth/register",
            data: JSON.stringify(userData),
            dataType: "json",
            headers: {
                "Authorization": "Bearer " + yourJWTToken
            },
            success: function(response) {
                // Handle successful registration
                alert("Registration successful!");
            },
            error: function(error) {
                // Handle registration error
                alert("Registration failed: " + error.responseJSON.message);
            }
        });
    });
});
