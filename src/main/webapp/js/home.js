sendToServer()
function sendToServer() {
	var JSONObject = [],obj = {};
	obj.requestListType='1'
	obj.currencyType='IND'
	var arr1 = JSON.stringify(obj)
	var arr = JSON.stringify(obj).substring(1, JSON.stringify(JSONObject).length - 1);
	var xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange=function(){
	            if(xmlHttp.readyState == 4 ){
	                if(xmlHttp.status == 200){
						console.log(xmlHttp.responseText);
					}
				}
		}
	xmlHttp.open("GET", "http://localhost:8090/transaction/management/transactions");
	xmlHttp.setRequestHeader("Content-type","application/json");
	xmlHttp.setRequestHeader("type", "RequestWSE");
	xmlHttp.send(JSON.stringify(obj))
}