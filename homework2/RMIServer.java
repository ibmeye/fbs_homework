import javax.naming.Context;
import javax.naming.InitialContext;



public class RMIServer {
	public static void main(String[] args){
		try {
			Context namingContext = new InitialContext();
			namingContext.rebind("rmi:HelloService", new HelloServiceImpl("HelloService"));
			System.out.println("Ready...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
