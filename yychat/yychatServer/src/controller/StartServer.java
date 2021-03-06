package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.yychat.model.Message;
import com.yychat.model.User;

public class StartServer {
	ServerSocket ss;
	Socket s;
	Message mess;
	String userName;
	String passWord;
	ObjectOutputStream oos;
	
	
	
	public static HashMap hmSocket=new HashMap<String,Socket>();//泛型，通用类
	
	public StartServer(){
		try {
	        ss=new ServerSocket(3456);//服务器端口监听3456
	        System.out.println("服务器已经启动，监听3456端口...");
	        while(true){//?多线程问题
	        	s=ss.accept();//等待客户端建立连接
	            System.out.println(s);//输出连接对象
	        
	            //字节输入流 包装成 对象输入流
	            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
	            User user=(User)ois.readObject();//接受用户登录对象user
	            userName=user.getUserName();
	            passWord=user.getPassWord();
	            System.out.println(userName);
	            System.out.println(passWord);
	            //注册新用户步骤7：在服务器端完成新用户的注册
	            if(user.getUserMessageType().equals("USER_REGISTER")){
	            	//注册新用户步骤8：对注册用户名进行查询,seekSuccess为true，存在同名用户，为false，不存在同名用户
	            	boolean seekSuccess=YychatDbUtil.seekUser(userName);
	            	mess=new Message();
 		            mess.setSender("Server");
 	        	    mess.setReceiver(userName);
	            	
	            	
	            	if(seekSuccess){
	            		//返回注册失败的message
	            		mess.setMessageType(Message.message_RegisterFailure);
	            	}else{
		            	//注册新用户步骤9：如果没有同名用户，把新用户的名字和密码写入到user表中，并返回注册成功的message
	            		YychatDbUtil.addUser(userName,passWord);
	 	        	    mess.setMessageType(Message.message_RegisterSuccess);
	            	}
		            sendMessage(s,mess);
	            	s.close();//注册结束，应该关闭socket对象
	            }
	            
	            
	            if(user.getUserMessageType().equals("USER_LOGIN")){
	            	 //使用数据库来验证用户名和密码
		            boolean loginSuccess=YychatDbUtil.loginValidate(userName,passWord);
		            mess=new Message();
		            mess.setSender("Server");
	        	    mess.setReceiver(user.getUserName());
	        	    if(loginSuccess){
	        	    //消息传递，创建Message对象
	        	    	mess.setMessageType(Message.message_LoginSuccess);
	        	    	//从数据库relation表中读取好友信息来更新好友列表1、服务器读取好友名字
		        	    String friendString=YychatDbUtil.getFriendString(userName);   
		        	    mess.setContent(friendString);
		        	    System.out.println(userName+"的全部好友："+friendString);
		            }
		            else{
		        	    mess.setMessageType(Message.message_LoginFailure);//验证不通过
		            }
		            sendMessage(s,mess);
		            
		            if(loginSuccess){
		            	//激活新上线好友的图标步骤1、向其他所有用户（比该用户先登录）发送信息
		            	mess.setMessageType(Message.message_NewOnlineFriend);
		            	mess.setSender("Server");
		            	mess.setContent(this.userName);//激活图标的用户名
		            	Set friendSet=hmSocket.keySet();
		            	Iterator it=friendSet.iterator();
		            	String friendName;
		            	while(it.hasNext()){
		            		friendName=(String)it.next();//取出一个好友名字
		            		mess.setReceiver(friendName);
		            		Socket s1=(Socket)hmSocket.get(friendName);
		            		/*oos=new ObjectOutputStream(s1.getOutputStream());
		            		 oos.writeObject(mess);*/
		            		sendMessage(s1,mess);
		            	}
		            	
		            	
		            	//保存每一个用户的Socket
		                hmSocket.put(userName, s);
		                System.out.println("保存用户的Socket"+userName+s);
		                //如何接收客户端的聊天信息？另建一个线程来接收聊天信息
		                new ServerReceiverThread(s,hmSocket).start();//创建线程，并让线程就绪
		                System.out.println("启动线程成功");
		            }
		            
		        }
	            }
	           

		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}


	private void sendMessage(Socket s,Message mess) throws IOException {
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
	}

	
	public static void main(String[] args) {
		

	}

}
