package com.yychat.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.User;

public class ClientConnetion {
	Socket s;
	public ClientConnetion(){
		try {//�쳣�����ṹ
			s=new Socket("127.0.0.1",3456);//������ַ���ز��ַ
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void loginValidate(User user){
		//��������������
		ObjectOutputStream oos;
		try {//���ֽ���������� ��װ�� �������������
			 oos=new ObjectOutputStream(s.getOutputStream());
			 oos.writeObject(user);//
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}