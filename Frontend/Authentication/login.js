$(document).ready(function() {
    // Login form submission logic
    $('#loginForm').submit(function(e) {
        e.preventDefault();
        
        // Get input values
        const username = $('#username').val();
        const password = $('#password').val();

        // Simulate authentication (replace with your actual authentication logic)
        if (username === 'yourusername' && password === 'yourpassword') {
            alert('Login successful! Redirecting to the Home Page.');
            // You can add a redirection here to your Home Page
        } else {
            alert('Login failed. Please check your username and password.');
        }
    });
});
