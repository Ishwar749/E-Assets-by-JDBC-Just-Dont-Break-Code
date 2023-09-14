//Array of user object --dummy data
let users = [
  {
    id : 1,
      name: "Ram",
     role: "Admin",
      mobile: 9726543212,
      email: "admin@gmail.com",
     username: "Ram",
     Last_Login_Time: "2023-09-14 04:30:30" 
        
  },
  {
    id : 2,
    name: "Priya Kote",
   role: "Borrower",
    mobile: 8766543212,
    email: "pkote@gmail.com",
   username: "Priya12",
   Last_Login_Time: "2022-01-01 05:20:20" ,
   isBanned:true  
},
{
    id:3,
    name: "Radhav Reddy",
   role: "Borrower",
    mobile: 8219543212,
    email: "raghav12@gmail.com",
   username: "Raghav2",
   Last_Login_Time: "2023-08-01 06:40:30",  
   isBanned:false
},
{
    id:4,
    name: "Riya",
   role: "Borrower",
    mobile: 9076543212,
    email: "riya123@gmail.com",
   username: "Riya2001",
   Last_Login_Time: "2023-06-01 03:20:30"  ,
   isBanned:false 
},
{
    id:5,
    name: "Shesh",
   role: "Borrower",
    mobile: 8012143212,
    email: "shesh3@gmail.com",
   username: "Shesh2",
   Last_Login_Time: "2023-09-01 08:20:30" ,
   isBanned:true   
},
  {
    id:6,
  name: "Siya Rane",
 role : "Borrower",
  mobile: 9836112451,
  email: "siya12@gmail.com",
  username: "Siyaa",
  Last_Login_Time: "2021-09-10 11:20:30",
  isBanned:false
},
{
    id:7,
  name: "Sarika",
 role : "Borrower",
  mobile: 8036112451,
  email: "sarika45@gmail.com",
  username: "Sarika5",
  Last_Login_Time: "2021-09-10 11:20:30",
  isBanned:false
},
{
    id:8,
  name: "Nikita",
 role : "Borrower",
  mobile: 9006112451,
  email: "nikita12@gmail.com",
  username: "Niku12",
  Last_Login_Time: "2021-09-10 11:20:30",
  isBanned:false
}];

const foundAdmin = users.find(user => user.role === 'Admin');
document.getElementById("admin-name").innerHTML=foundAdmin.name;

// Get a reference to the table body
      let tableBody = document.querySelector('#userTable tbody');

      // Loop through the JSON data and populate the table
      for (let i = 0; i < users.length; i++) {

        if (users[i].role === "Admin") {
            // Skip displaying the row for admin
            continue;
        }
        
          let row = tableBody.insertRow();
          let cell1 = row.insertCell(0);
          let cell2 = row.insertCell(1);
          let cell3 = row.insertCell(2);
          let cell4 = row.insertCell(3);
          let cell5 = row.insertCell(4);

          cell1.textContent = users[i].id;
          cell2.textContent = users[i].name;
          cell3.textContent = users[i].mobile;
          cell4.textContent = users[i].email;
          cell5.textContent = users[i].Last_Login_Time;

          if(users[i].isBanned===true){
          const rowToHighlight = row // For example, highlight the second row (index 1)

          // Add the CSS class to the specific row
           rowToHighlight.classList.add('highlighted-row');
      }
    }
 

        function myFunction()
        {
            exd = new Date();
            var hours=exd.getHours();
            var min=exd.getMinutes();
        var sec=exd.getSeconds();
        var dat=exd.getDate();
        var month=exd.getMonth();
        var year=exd.getFullYear();
        var dn="AM";if(hours==12){
          
        dn="PM";
    }
    if(hours>12){dn="PM";
        hours=hours-12;}
        if(hours<10){
        hours="0"+hours;}
        if(min<10)
        {min="0"+min;}if(sec<10){
        sec="0"+sec;}if(dat<10){
        dat="0"+dat;}if(month<10){
        month="0"+month;}
        var clock=hours+":"+min+":"+sec+""+dn;var datee=dat+"-"+ month+"-"+year;
        var id=document.getElementById("datme"); 
        document.getElementById("demo").innerHTML = "You Login at "+clock+"  "+datee;
        }



