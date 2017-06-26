package com.duapp.ibmeye;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws Exception{
//		BufferedWriter file_writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File("E://s_a.txt") )) );
//		System.out.println("good");
//		ServerSocket ss = new ServerSocket(8088);
//		Socket socket = ss.accept();
//		
//        String ip = ss.getInetAddress().getHostAddress();  
//        System.out.println(ip + "...连接成功！");  
//        
//		BufferedReader soc_reader = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
//		
//		char[] chs = new char[4096];
//		int index = -1;
//		while( (index = soc_reader.read(chs)) != -1 ) {
//			file_writer.write(chs,0,index);
//		}
//		
//		BufferedWriter soc_writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
//		soc_writer.write("successful");
//		soc_writer.flush();
//		
//		soc_writer.close();
//		file_writer.close();
//		socket.close();
//		ss.close();
		
		
		ServerSocket ss = new ServerSocket(10080);
		Socket socket = ss.accept();
		
		
		OutputStream file =  new FileOutputStream( new File("E://s_a.txt") ) ;		
		InputStream in = socket.getInputStream();
		
		byte[] chs = new byte[4096];
		int index = -1;
		while( (index = in.read(chs)) != -1 ) {
			file.write(chs,0,index);
		}
		socket.shutdownInput();
		
		OutputStream out = socket.getOutputStream();
		out.write("successful".getBytes());
		socket.shutdownOutput();

		socket.close();
		ss.close();
	}
}
