
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class Util {
	public static String dic(String word) throws Exception {
		BufferedReader reader = null;
		try {
			StringBuffer buffer = new StringBuffer();
			
			URL url = new URL("http://dict-co.iciba.com/api/dictionary.php?w=" + word + "&key=873FA40C0B607AEED1F6B56A8BE56F31&type=json");
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			int index = -1;
			char[] chs = new char[1024];
			while( (index = reader.read(chs)) != -1) {
				buffer.append(chs,0,index);
			}
			
			return buffer.toString();
		}
		finally {
			if ( reader != null ) {
				reader.close();
			}
		}
	}
}
