let dataArray = [];

        function handleFileUpload() {
            const fileInput = document.getElementById('fileInput');
            const resultElement = document.getElementById('result');

            if (fileInput.files.length === 0) {
                alert('Please select a JSON file to add Users');
                return;
            }

            const file = fileInput.files[0];
            const reader = new FileReader();

            reader.onload = function(event) {
                const fileContent = event.target.result;

                try {
                    const jsonData = JSON.parse(fileContent);

                    if (Array.isArray(jsonData)) {
                        // Assuming JSON data is an array of objects
                        const uniqueObjects = getUniqueObjects(dataArray.concat(jsonData));
                        const addedObjects = uniqueObjects.length - dataArray.length;

                        dataArray = uniqueObjects;
                        resultElement.textContent = `Successfully added ${addedObjects} Users.`;
                        
                    } else {
                        alert('The JSON data should be an array.');
                    }
                } catch (error) {
                    alert('Error parsing the JSON file: ' + error.message);
                }
            };

            reader.readAsText(file);
        }

        function getUniqueObjects(array) {
            // Create a Set to store unique objects based on a custom key
            const uniqueSet = new Set();
            const uniqueArray = [];

            for (const obj of array) {
                // Convert the object to a JSON string for comparison
                const objString = JSON.stringify(obj);

                // Add the object to the Set (duplicates will be automatically removed)
                uniqueSet.add(objString);
    
            }

            // Regular expression for a valid email address
            const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

            // Convert the Set back to an array of objects
            for (const objString of uniqueSet) {
                const objs=JSON.parse(objString);
                // Test the email against the regular expression
                if(emailRegex.test(objs.Email)){
                uniqueArray.push(objs);
            }
        }
        
            return uniqueArray;
        }