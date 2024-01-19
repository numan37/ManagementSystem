obj = {};
selectedTab = "INR",
transactionsArray=[]

obj.requestListType='1'
obj.currencyType='INR'
getLisitng(obj)

function onTabChange(){
	obj.requestListType='1'
	obj.currencyType=document.getElementById("currencyInListing").value;
	getLisitng(obj)
}

function unhideTab(){
	$("#inputDateField").show();
}

function searchBasedOnDate(){
	obj.requestListType='2'
	obj.currencyType=document.getElementById("currencyInListing").value;
	obj.dateOfTransaction=document.getElementById("dateOfTransaction").value;
	getLisitng(obj)
}

function getLisitng(obj) {
	
	var arr1 = JSON.stringify(obj)
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
	            if(xmlHttp.readyState == 4 ){
	                if(xmlHttp.status == 200){
						console.log(xmlHttp.responseText);
						var res=xmlHttp.responseText;
						var newRecArray=JSON.parse(res)
						if(newRecArray.length>0){
							transactionsArray=transactionsArray.concat(newRecArray);
							showStandardViewData(newRecArray)
						} else{
							$("#noRecordsDiv").show();
						}
					}
				}
		}
	xmlHttp.open("POST", "/base/transaction/management/transactions");
	xmlHttp.setRequestHeader("Content-type","application/json");
	xmlHttp.send(arr1)
}

function showStandardViewData(dataArray){
	var table = document.getElementById("results");
	clearListData()
	for (var listIndex = 0; listIndex < dataArray.length; listIndex++) {
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        cell1.innerHTML=dataArray[listIndex].record_id
        cell2.innerHTML=dataArray[listIndex].transactionType
        cell3.innerHTML=dataArray[listIndex].transactionMoney
        cell4.innerHTML=dataArray[listIndex].transactionDate
        cell5.innerHTML=dataArray[listIndex].reasonOfTransaction
    }
	
}

function clearListData() {
    /*Remove all rows from table*/
    var table = document.getElementById("results");

    while (table.hasChildNodes()) {
        table.removeChild(table.firstChild);
    }

}