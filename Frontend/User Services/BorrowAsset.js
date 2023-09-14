
        // Simulated asset data (replace with actual data retrieval logic)
        const assets = [{
            id: "1",
            name: "Laptop",
            category : "Laptop" ,
            status: "Available"     
        },
        {
            id: "2",
            name: "Headset",
            category : "Headset" ,
            status: "Available"     
        },
        {
            id: "3",
            name: "mobile",
            category : "Mobile",
            status: "Available"      
        }
    ];

    UserAssetRel = [
        {UserId:2,
        AssetIds:[1,3]},
        {UserId:3,
        AssetIds:[1,4]}
];
   

    const searchForm = document.getElementById("search-form");
    const issueForm = document.getElementById("issue-form");
    const resultsDiv = document.getElementById("results");
    const assetInfo = document.getElementById("asset-info");
    const issueButton = document.getElementById("issue-button");

    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();

        // Simulate asset search
        const assetName = document.getElementById("asset-name").value;

        const foundAsset = assets.find(asset => asset.name.toLowerCase() === assetName.toLowerCase());
        //const foundUser = UserAssetRel.UserId===2;
        const borrowedcategory = UserAssetRel[0].AssetIds;

        if (foundAsset) {

            if((borrowedcategory.includes(foundAsset.assetId)==false)){
            assetInfo.textContent = `Asset Name: ${foundAsset.name}, Asset ID: ${foundAsset.id}`;
            resultsDiv.style.display = "block";

            if (foundAsset.status === "Available") {
                issueButton.style.display = "block";
            } else {
                issueButton.style.display = "none";
            }
        }
        else { assetInfo.textContent = "Only one asset of same catagory can borrowed";
        resultsDiv.style.display = "block";
        issueButton.style.display = "none";}

        } else {
            assetInfo.textContent = "Asset not found.";
            resultsDiv.style.display = "block";
            issueButton.style.display = "none";
        }
    });

    issueButton.addEventListener("click", function () {
        resultsDiv.style.display = "none";
        issueForm.style.display = "block";
    });

    issueForm.addEventListener("submit", function (e) {
        e.preventDefault();

        // Simulate asset issuance
        const userName = document.getElementById("user-name").value;

        // Find the asset again by name
        const foundAsset = assets.find(asset => asset.name.toLowerCase() === document.getElementById("asset-name").value.toLowerCase());

        if (foundAsset) {
            foundAsset.status = "Issued";
            alert(`Asset "${foundAsset.name}" has been issued to ${userName}.`);
            // Reset the forms and hide the issue form
            searchForm.reset();
            issueForm.reset();
            issueForm.style.display = "none";
            resultsDiv.style.display = "none";
        }
    });