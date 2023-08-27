(function() {
    document.getElementById('submitButton').addEventListener('click', function() {
        var fields = ['fname', 'lname', 'email', 'country', 'subject'];

        fields.forEach(function(field) {
            var inputElement = document.getElementById(field);
            var errorElement = document.getElementById(field + 'Error');

            if (!inputElement.value) {
                if (errorElement) {
                    errorElement.textContent = 'Campo obbligatorio';
                }
            } else {
                if (errorElement) {
                    errorElement.textContent = ''; // Rimuovi l'eventuale messaggio di errore precedente
                }
            }
        });
    });
})();