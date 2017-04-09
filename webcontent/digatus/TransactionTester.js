TransactionTester.prototype.count_id = 0;

/**
	in search_id
	out idResponse
*/
TransactionTester.prototype.getTransactionById = function(){
	var id 	= document.getElementById("search_id").value;
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
		   document.getElementById("idResponse").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "transactionservice/transaction/"+id, true);
	xhttp.send();
}
/**
	in search_type
	out typeResponse
*/
TransactionTester.prototype.getIdsByType = function(){
	var type 	= document.getElementById("search_type").value;
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
		   document.getElementById("typeResponse").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "transactionservice/types/"+type, true);
	console.log("transactionservice/types/"+type);
	xhttp.send();
}
/**
	in sum_id
	out sumResponse
*/
TransactionTester.prototype.sumById = function(){
	var id 	= document.getElementById("sum_id").value;
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
		   document.getElementById("sumResponse").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "transactionservice/sum/"+id, true);
	xhttp.send();
}
/**
	in transaction_amount
	in transaction_type
	in transaction_parent_id
	in generated ID
	out putResponse
*/
TransactionTester.prototype.putTransaction = function(){
	var amount 		= document.getElementById("transaction_amount").value;
	var type 		= document.getElementById("transaction_type").value;
	var parent_id	= document.getElementById("transaction_parent_id").value;
	var id			= TransactionTester.prototype.count_id++;
	
	var transaction = new Transaction(id, amount, type);
	if(parent_id != null && parent_id != "")
		transaction.parent_id = parent_id;
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
		   document.getElementById("putResponse").innerHTML =xhttp.responseText;
		}
	};
	xhttp.open("PUT", "transactionservice/transaction/"+transaction, true);
	console.log("transactionservice/transaction/"+transaction);
	xhttp.send();
}
function TransactionTester(){
}