import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class RMIClient {
	public static void main(String[] args) {
		String url =  "rmi://localhost/";
		try {
			Context namingContext = new InitialContext();
			HelloService service =  (HelloService)namingContext.lookup(url+"HelloService");
			
			System.out.println(service.getTime());
			
			
			//System.out.println(args[0]);
			String str = "welcome";
			if( args.length == 1 ) {
			    str = args[0];
			}
			
			String json = service.translate(str);
//			assert json != null;
			if( json == null ) throw new Exception();
			Word word = new Gson().fromJson(json, Word.class);
			System.out.println(word.getWord_name());
			List<Part> parts = word.getSymbols().get(0).getParts();
			for (Part part : parts) {
				System.out.print(part.getPart() + " ");
				System.out.println(part.getMeans());
			}
						
		}
		catch(Exception e) {
			System.out.println("这条词汇还没有收录");
		}
		
	
		
	}
	
	
}
