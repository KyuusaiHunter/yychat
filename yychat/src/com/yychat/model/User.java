package com.yychat.model;

import java.io.Serializable;

public class User implements Serializable{
	private String userName;//��Ա����
	private String passWoed;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;//�ֲ���������Ա������ֵ
	}
	public String getPassWoed() {
		return passWoed;
	}
	public void setPassWoed(String passWoed) {
		this.passWoed = passWoed;
	}

	
}