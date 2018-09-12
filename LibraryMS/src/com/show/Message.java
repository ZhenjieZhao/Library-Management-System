package com.show;

import javax.swing.*;

public class Message extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel message;
	public Message(String m){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		message = new JLabel(m);
		message.setBounds(10, 10, 150, 40);
		panel.add(message);
		
		this.add(panel);
		this.setSize(200,200);
		this.setTitle("Message");
		this.setVisible(true);
	}

}
