Transaction.prototype.amount;
Transaction.prototype.type;
Transaction.prototype.id;
Transaction.prototype.parent_id;

Transaction.prototype.toString = function(){
	var basic = '"amount":'+this.amount+', "type": "'+this.type+'"';
	var id = this.id;
	if(this.parent_id != null)
		basic += ', "parent_id":'+id;
	return id+' {'+basic+'}';
}

function Transaction(id, amount, type){
	this.id = id;
	this.amount = amount;
	this.type = type;
}