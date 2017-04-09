import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;

public class TransactionContainer extends HttpServlet implements Iterable<Transaction>, TransactionService{
	
	private Vector<Transaction> transactions = new Vector<Transaction>();
	
	public String putTransaction(String uri) throws Exception{
		uri = uri.replace("/digatus/transactionservice/transaction/", "");
		
		String str_id = uri.substring(0, uri.indexOf("{")).trim();
		String str_json = uri.substring(uri.indexOf("{"));
		
		Transaction transaction = new Transaction(str_id, str_json);
		if(transaction.getParent_id()!= -1)
			getTransactionById(transaction.getParent_id()).addChild(transaction);
		add(transaction);
		
		return "{ \"status\": \"ok\" }";
	}

	@Override
	public String getTransactionById(String uri) throws Exception {
		uri = uri.replace("/digatus/transactionservice/transaction/", "").trim();
		long id = Long.parseLong(uri);
		return getTransactionById(id).toString();
	}

	@Override
	public String getTransactionIdsByType(String uri) throws Exception {
		uri = uri.replace("/digatus/transactionservice/types/", "");
		String type = uri.trim();
		Vector<Long> ids = new Vector<Long>();
		for(Transaction trans: this)
			if(trans.getType().equals(type))
				ids.add(trans.getId());
		return ids.toString();
	}

	@Override
	public String sumByTransactionId(String uri) throws Exception {
		uri = uri.replace("/digatus/transactionservice/sum/", "").trim();
		long id = Long.parseLong(uri);
		double sum = getTransactionById(id).sum();
		return "{\"sum\": "+sum+"}";
	}
	
	protected void add(Transaction transaction){
		transactions.add(transaction);
	}
	private Transaction getTransactionById(long id) throws Exception {
		for(Transaction t: this)
			if(t.getId() == id)
				return t;
		throw new Exception("ID not found");
	}
	@Override
	public Iterator<Transaction> iterator() {
		return transactions.iterator();
	}
}
