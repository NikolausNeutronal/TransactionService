import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TransactionServlet extends TransactionContainer {
	static final boolean test = true;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = null;
		String uri = encode(request.getRequestURI());
		try{
			if(uri.contains("/transactionservice/transaction/"))
				status = this.getTransactionById(uri);
			else if(uri.contains("/transactionservice/types/"))
				status = this.getTransactionIdsByType(uri);
			else if(uri.contains("/transactionservice/sum/"))
				status = this.sumByTransactionId(uri);
		}catch(Exception e){
			status = e.getMessage();
		}
		
		response.setIntHeader("Refresh", 5);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(status);
		out.close();
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = null;
		String uri = encode(request.getRequestURI());
		try{
			status = putTransaction(uri);
		}catch(Exception e){
			status = e.getMessage();
		}
		
		response.setIntHeader("Refresh", 5);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(status);
		out.close();
	}
	
	protected String encode(String uri){
		uri = uri.replace("%2F", "/");
		uri = uri.replace("%257B", "{");
		uri = uri.replace("%7B", "{");
		uri = uri.replace("%257D", "}");
		uri = uri.replace("%7D", "}");
		uri = uri.replace("%2522", "\"");
		uri = uri.replace("%22", "\"");
		uri = uri.replace("%3A", ":");
		uri = uri.replace("%2C", ",");
		uri = uri.replace("%2520", " ");
		uri = uri.replace("%20", " ");
		return uri;
	}
}
