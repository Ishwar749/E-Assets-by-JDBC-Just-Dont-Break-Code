    //Array of User object
        let userData =[ 
            {
            id: 2,
            name: "JohnDoe",
            email: "johndoe@gmail.com",
            mobile: 7890264510
        },
        {
            id: 3,
            name: "riya",
            email: "riyya@example.com",
            mobile: 7890264510
        }
    ];

        let foundUser = userData.find(user => user.id === 2);
        document.getElementById("name").innerHTML=foundUser.name;
        document.getElementById("email").innerHTML=foundUser.email;
        document.getElementById("mob").innerHTML=foundUser.mobile;

//Asset info
let assets = [
    {
        assetId : 1,
        assetName : "Redmi note 9",
        assetType : "Mobile",
        borrowedDate : "2022-8-2",
        dueDate : "2022-8-10",
        lateReturnFee : 200,
        status: "Issued"
    },
    {
        assetId : 2,
        assetName : "Dell Laptop",
        assetType : "Laptop",
        borrowedDate : "2023-9-5",
        dueDate : "2023-9-15",
        lateReturnFee : 200,
        status: "Issued"
    }
];

// Get a reference to the table body
let tableBody = document.querySelector('#assetTable tbody');

// Loop through the assets array and populate the table
for (let i = 0; i < assets.length; i++) {

    let row = tableBody.insertRow();
    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let cell3 = row.insertCell(2);
    let cell4 = row.insertCell(3);
    let cell5 = row.insertCell(4);
    let cell6 = row.insertCell(5);
    let cell7 = row.insertCell(6);
    let cell8 = row.insertCell(7);
    
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.value = assets[i].assetId;
    cell8.appendChild(checkbox);
    
    cell1.textContent = assets[i].assetId;
    cell2.textContent = assets[i].assetName;
    cell3.textContent = assets[i].assetType;
    cell4.textContent = assets[i].borrowedDate;
    cell5.textContent = assets[i].dueDate;
    cell6.textContent = assets[i].lateReturnFee;
    cell7.textContent = assets[i].status;
    
}  


  const messageDiv = document.getElementById("message");
  
    returnButton.addEventListener("click", function () {
    const selectedAssetIds = Array.from(assetTable.querySelectorAll('input[type="checkbox"]:checked'))
        .map((checkbox) => parseInt(checkbox.value));

    if (selectedAssetIds.length === 0) {
        messageDiv.textContent = "Please select assets to return.";
        messageDiv.style.display = "block";
    } else {
        selectedAssetIds.forEach((id) => {
            const asset = assets.find((asset) => asset.assetId === id);
            if (asset && asset.status === "Issued") {
                asset.status = "Available";
            }
        });
        
        messageDiv.textContent = `Selected assets have been returned.`;
        messageDiv.style.display = "block";
    }
});

// Sample array of objects with messages
const messagesArray = [
    { id: 1, message: "Mobile due date is 2022-8-10" },
    { id: 2, message: "Laptop due date is 2023-9-15"}
];

// Function to display messages when the message is clicked in header
document.getElementById("show-messages").addEventListener("click", function () {
    messagesArray.forEach((item) => {
        alert(item.message);
    });
});




           

