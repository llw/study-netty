package com.llw.study.netty.basis.javalib.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServerV2 {

	
	public static void main(String args[]){
		int port =8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:"+port);
			Socket socket =  null;
			TimeServerHandlerExecutePool executor =new TimeServerHandlerExecutePool(50, 10000);
			while(true){
				socket = server.accept();
				executor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(server !=null){
				System.out.println("the time server close");
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				server=null;
			}
		}
	}

}
