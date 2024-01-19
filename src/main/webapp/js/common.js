function navigateToHome(){
	window.location.href ="home.html"
}

function submitData(){
	var value1= document.getElementById("rupeesField").value;
	var value2= document.getElementById("currency").value;
	var value3= document.getElementById("transactionType").value;
	var value4= document.getElementById("reasonforTrans").value;
	obj = {};
	obj.transactionType=value3
	obj.transactionMoney=parseFloat(value1)
	obj.reasonOfTransaction=value4
	obj.currencyType=value2
	var arr1 = JSON.stringify(obj)
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
	            if(xmlHttp.readyState == 4 ){
	                if(xmlHttp.status == 200){
						console.log(xmlHttp.responseText);
					}
				}
		}
	xmlHttp.open("POST", "/base/transaction/management/transaction/create");
	xmlHttp.setRequestHeader("Content-type","application/json");
	xmlHttp.setRequestHeader("type", "RequestWSE");
	xmlHttp.send(arr1)
}

function submitToServer() {
	var JSONObject = [],obj = {};
	obj.requestListType='1'
	obj.currencyType='INR'
	var arr1 = JSON.stringify(obj)
	var arr = JSON.stringify(obj).substring(1, JSON.stringify(JSONObject).length - 1);
	var gsonObj = "{obj:" + arr1 + "}";
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
	            if(xmlHttp.readyState == 4 ){
	                if(xmlHttp.status == 200){
						console.log(xmlHttp.responseText);
					}
				}
		}
	xmlHttp.open("POST", "/base/transaction/management/transactions");
	xmlHttp.setRequestHeader("Content-type","application/json");
	xmlHttp.setRequestHeader("type", "RequestWSE");
	xmlHttp.send(arr1)
}

