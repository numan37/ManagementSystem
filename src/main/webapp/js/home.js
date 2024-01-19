sendToServer()
function sendToServer() {
	var JSONObject = [],obj = {};
	obj.requestListType='1'
	obj.currencyType='IND'
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