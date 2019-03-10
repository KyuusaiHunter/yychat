package com.yychat.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FriendList extends JFrame implements ActionListener{//����

	//��Ա����
	CardLayout cardLayout;
	//��һ�ſ�Ƭ
    JPanel myFriendPanel;
    JButton myFriendButton;//����
    
    JScrollPane myFriendListJScrollPane;
    JPanel myFriendListJPanel;
    public static final int MYFRIENDCOUNT=51;
	private static final int MYFRIENDCOUNT1 = 0;
    JLabel[] myFriendJLabel=new JLabel[MYFRIENDCOUNT];//50�������飬�Բ�����
    
    JPanel	myStrangerBlackListPanel;
    JButton myStrangerButton;
    JButton blackListButton;
    
    //�ڶ�����Ƭ
    JPanel myStrangerPanel;
    //����
    JPanel myFriendStrangerPanel;
    JButton myFriendButton1;
    JButton myStrangerButton1;
    //�в�
    JPanel myStrangerListJPanel;
    public static final int MYFRIENDCOUNT11=51;
    JLabel[] myStrangerJLabel=new JLabel[MYFRIENDCOUNT11];
    JScrollPane myStrangerListJScrollPane;
    
    //�ϲ�
    JButton blackListButton1;
	
    //�����ſ�Ƭ
    
    
    public  FriendList(){
    	//������һ�ſ�Ƭ
    	myFriendPanel=new JPanel(new BorderLayout());//�������⣬�߽粼��
    	//System.out.println(myFriendPanel.getLayout());
        
    	//����
    	myFriendButton= new JButton("�ҵĺ���");
    	myFriendPanel.add(myFriendButton,"North");
    	
    	//�в�
    	myFriendListJPanel=new JPanel(new GridLayout(MYFRIENDCOUNT-1,1));//���񲼾�
    	for(int i=1;i<MYFRIENDCOUNT;i++){
			myFriendJLabel[i]=new JLabel(i+"",new ImageIcon("images/yy0.gif"),JLabel.LEFT);
			myFriendListJPanel.add(myFriendJLabel[i]);   
		}
    	/*myFriendListJScrollPane=new JScrollPane();
    	myFriendListJScrollPane.add(myFriendListJPanel);*/
    	myFriendListJScrollPane=new JScrollPane(myFriendListJPanel);
    	myFriendPanel.add(myFriendListJScrollPane);
    	
    	//�ϲ�
    	myStrangerBlackListPanel=new JPanel(new GridLayout(2,1));//���񲼾�
    	//System.out.println(myStrangerBlackListPAnel.getLayout());
    	myStrangerButton=new JButton("�ҵ�İ����");
    	myStrangerButton.addActionListener(this);//�¼�����
    	blackListButton=new JButton("������");
    	myStrangerBlackListPanel.add(myStrangerButton);
    	myStrangerBlackListPanel.add(blackListButton);
    	myFriendPanel.add(myStrangerBlackListPanel,"South");
    	
    	
    	//�����ڶ��ſ�Ƭ
    	myStrangerPanel=new JPanel(new BorderLayout());//���ֵ����⣬�߽粼��
    	//����
    	myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
    	myFriendButton1=new JButton("�ҵĺ���");
    	myFriendButton1.addActionListener(this);//ʱ�����
    	myStrangerButton1 = new JButton("�ҵ�İ����");
    	myFriendStrangerPanel.add(myFriendButton1);
    	myFriendStrangerPanel.add(myStrangerButton1);
    	myStrangerPanel.add(myFriendStrangerPanel,"North");
    	
    	
    	//�в�
    	myStrangerListJPanel = new JPanel(new GridLayout(MYFRIENDCOUNT-1,1));//���񲼾�
    	for(int i=1;i<MYFRIENDCOUNT;i++){
			myStrangerJLabel[i]=new JLabel(i+"",new ImageIcon("images/yy4.gif"),JLabel.LEFT);
			myStrangerListJPanel.add(myStrangerJLabel[i]);   
		}
    	/*myStrangerListJScrollPane=new JScrollPane();
    	myStrangerListJScrollPane.add(myFriendListJPanel);*/
    	myStrangerListJScrollPane=new JScrollPane(myStrangerListJPanel);
    	myStrangerPanel.add(myStrangerListJScrollPane);
    	//�ϲ�
    	blackListButton1=new JButton("������");
    	myStrangerPanel.add(blackListButton1,"South");
    	//����������Ƭ
    	cardLayout=new CardLayout();//��Ƭ����    
    	this.setLayout(cardLayout);
    	this.add(myFriendPanel,"1");
    	this.add(myStrangerPanel,"2");
    	
    	
    	this.setSize(150,500);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    }
    
    public static void main(String[] args){
    	FriendList friendList=new FriendList();
    }

	@Override
	public void actionPerformed(ActionEvent e) {//��Ӧ�¼��ķ���
		// TODO Auto-generated method stub
		if(e.getSource()==myStrangerButton) cardLayout.show(this.getContentPane(),"2");
		if(e.getSource()==myFriendButton1) cardLayout.show(this.getContentPane(),"1");
		
	}
    
    
    
}