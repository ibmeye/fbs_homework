package com.duapp.ibmeye;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1",10080);
		
		InputStream file =  new FileInputStream("E://a.txt") ;
		OutputStream out = socket.getOutputStream();
		byte[] chs = new byte[1024];
		int index = -1;
		while( (index = file.read(chs)) != -1 ) {
			out.write(chs,0,index);
		}
		file.close();
		socket.shutdownOutput();
		
	    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()) );  
	    System.out.println(reader.readLine());
		socket.shutdownInput();
		
		socket.close();
	
	}
}
