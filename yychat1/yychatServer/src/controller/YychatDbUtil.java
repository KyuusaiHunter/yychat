package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YychatDbUtil {
	//静态成员（字符串类型的符号常量）,也叫类成员
	public static final String MYSQLDRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
	public static final String DBUSER="root";
	public static final String DBPASS="";  //实例（对象）变量，yychat1=new YychatDbUtil();
	                          //             yychat2=new YychatDbUtil();  
	
    
	//1.加载驱动程序(静态方法),特点：在类加载的时候就能被加载
	public static void loadDriver(){
		try {
			Class.forName(MYSQLDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    
    //2.建立连接,默认GBK
    public static Connection getConnection(){
    	loadDriver();
    	Connection conn=null;
		try {
			conn = DriverManager.getConnection(URL,DBUSER,DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return conn;
    }
   
    
    //3.建立一个preparedStatement
    public static boolean loginValidate(String userName,String passWord){	
    	boolean loginSuccess=false;
    	Connection conn=getConnection();
    	String user_Login_Sql="select * from user where username=? and password=?";
        PreparedStatement ptmt=null;
        ResultSet rs=null;
		try {
			ptmt = conn.prepareStatement(user_Login_Sql);
			ptmt.setString(1,userName);
	        ptmt.setString(2,passWord);
	        //4.执行preparedStatement
	        rs=ptmt.executeQuery();
	        //5.判断结果集
	        loginSuccess=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn,ptmt,rs);
		}
        return loginSuccess;
    }
    
    public static String getFriendString(String userName){
    	Connection conn=getConnection();
    	String friend_Relation_Sql="select slaveuser from relation where majoruser=? and relationtype='1'";
    	PreparedStatement ptmt;
    	String friendString="";
		try {
			ptmt = conn.prepareStatement(friend_Relation_Sql);
			ptmt.setString(1,userName);
	        ResultSet rs=ptmt.executeQuery();
	        while(rs.next()){//移动结果集中的指针，一个个的取出好友的名字
	        	//rs.getString(1);
	        	friendString=friendString+rs.getString("slaveuser")+" ";
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return friendString;
    }
    public static void closeDB(Connection conn,PreparedStatement ptmt,ResultSet rs){
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(ptmt!=null){
    		try {
				ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
 }
