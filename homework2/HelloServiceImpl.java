import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{
	private String name;
	public HelloServiceImpl(String name) throws RemoteException {
		this.name = name;

	}
	@Override
	public String echo(String msg) throws RemoteException {
		System.out.println(name + "调用  echo() 方法");
		return "echo:" + msg + "from" + name;
	}
	@Override
	public Date getTime() throws RemoteException {
		System.out.println(name + "调用 getTime() 方法");
		return new Date();
	}
	
	@Override
	public String translate(String str) throws Exception {
		System.out.println(name + "调用  translate() 方法");
		Gson gson = new Gson();
		String json = Util.dic(str);
		Word word = null;
		try {
			word = gson.fromJson( json , Word.class);
//			assert word.getWord_name() != null; 
//			System.out.println(word.getWord_name());
			if( word.getWord_name() == null ) throw new Exception();
			
		}
		catch(Exception e) {
			return null;
		}
		return gson.toJson(word);
	}
}

