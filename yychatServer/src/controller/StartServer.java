package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.yychat.model.User;

public class StartServer {
	ServerSocket ss;
	Socket s;
	
	public StartServer(){
		try {
	        ss=new ServerSocket(3456);//服务器端口监听3456
	        System.out.println("服务器已经启动，监听3456端口...");
	        s=ss.accept();//等待客户端建立连接
	        System.out.println(s);//输出连接对象
	        
	        //字节输入流 包装成 对象输入流
	        ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
	        User user=(User)ois.readObject();
	        System.out.println(user.getUserName());
	        System.out.println(user.getPassWoed());
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		

	}

}
