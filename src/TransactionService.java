import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;

public interface TransactionService {
	/**
	 * 	transaction_id is a long specifying a new transaction
		amount is a double specifying the amount
		type is a string specifying a type of the transaction.
		parent_id is an optional long that may specify the parent transaction of this
		transaction.
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public abstract String putTransaction(String uri) throws Exception;
	/**
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public abstract String getTransactionById(String uri) throws Exception;
	/**
	 * 
	 * @param uri
	 * @return A json list of all transaction ids that share the same type $type.
	 * @throws Exception
	 */
	public abstract String getTransactionIdsByType(String uri) throws Exception;
	/**
	 * 
	 * @param uri
	 * @return A sum of all transactions that are transitively linked by their parent_id to
	 * @throws Exception
	 */
	public abstract String sumByTransactionId(String uri) throws Exception;
}
