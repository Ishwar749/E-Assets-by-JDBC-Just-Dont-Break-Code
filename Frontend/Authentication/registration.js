$(document).ready(function() {
    // Registration form submission logic
    $('#registrationForm').submit(function(e) {
        e.preventDefault();
        
        // Validate password complexity
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

        if (!passwordPattern.test(password)) {
            alert("Password must contain at least one capital letter, one lowercase letter, one number, and be at least 8 characters long.");
            return;
        }

        if (password !== confirmPassword) {
            alert("Passwords do not match.");
            return;
        }

        // If password complexity and confirmation are valid, proceed with form submission.
        // ... Add your code to submit the form to the server ...
    });
});
