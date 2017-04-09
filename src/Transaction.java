import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Transaction{
	private Vector<Transaction> children = new Vector<Transaction>();
	private long id;
	private long parent_id = -1;
	private double amount;
	private String type;
	
	private JSONObject json;
	
	public Transaction(String str_id, String str_json) throws ParseException, IllegalArgumentException{
		this.id = Long.parseLong(str_id);
		init((JSONObject) new JSONParser().parse(str_json));
	}
	
	private void init(JSONObject transaction)throws IllegalArgumentException{
		this.json = transaction;
		Object amount = transaction.get("amount");
		if(amount instanceof Double)
			this.amount = (Double)amount;
		else if(amount instanceof Long)
			this.amount = (Long)amount;
		else if(amount instanceof Integer)
			this.amount = (Integer)amount;
		else
			throw new IllegalArgumentException("amount is no number! "+amount);
		
		Object parent_id = transaction.get("parent_id");
		if(parent_id instanceof Long)
			this.parent_id = (Long) parent_id;
		else if(parent_id instanceof Integer)
			this.parent_id = (Integer) parent_id;
		else if(parent_id != null)
			throw new IllegalArgumentException("parent_id is no Number! "+type);
		
		Object type = transaction.get("type");
		if(type instanceof String)
			this.type = (String)type;
		else
			throw new IllegalArgumentException("type is no String! "+type);
	}
	
	@Override
	public String toString(){
		return json.toJSONString();
	}
	
	public long getId() {
		return id;
	}

	public long getParent_id() {
		return parent_id;
	}

	public double getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}
	
	public boolean addChild(Transaction trans){
		return children.add(trans);
	}
	
	public boolean removeChild(Transaction trans){
		return children.remove(trans);
	}
	
	public double sum(){
		double sum = amount;
		for(Transaction t: children)
			sum += t.sum();
		return sum;
	}
}
