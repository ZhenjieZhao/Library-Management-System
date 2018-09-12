package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.business.*;

public class CheckOut extends JFrame{
private static final long serialVersionUID = 1L;
	
	JLabel isbn;
	JTextField isbnInput;
	JLabel cardid;
	JTextField cardidInput;
	
	JButton jb;
	
	CheckOut(String ISBN){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		isbn = new JLabel("ISBN:");
		isbn.setBounds(10, 10, 80, 25);
		panel.add(isbn);
		
		isbnInput = new JTextField(ISBN);
		isbnInput.setBounds(100, 10, 80, 25);
		panel.add(isbnInput);
		
		cardid = new JLabel("Card_ID:");
		cardid.setBounds(10, 40, 80, 25);
		panel.add(cardid);
		
		cardidInput = new JTextField();
		cardidInput.setBounds(100, 40, 80, 25);
		panel.add(cardidInput);
		
		jb = new JButton("Check Out");
		jb.setBounds(50, 70, 100, 25);
		panel.add(jb);
		
		jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	if(!checkEmpty() && checkAval()){
            		if(addLoan()){
            			new Message("Successfully Create New Borrower!");
            		}else{
            			new Message("Unsuccessful!");
            		}
            	}
            	
            }
		});
		
		this.add(panel);
		this.setSize(200,200);
		this.setTitle("Update Borrower");
		this.setVisible(true);
	}
	
	public String getIsbn() {
        return isbnInput.getText().trim();
    }
	public String getCardId() {
        return cardidInput.getText().trim();
    }
	
	public boolean checkEmpty(){
		if(getIsbn().equals("") || getCardId().equals("") ){
			new Message("Please fill every field");
			return true;
		}
		return false;
	}
	
	public boolean checkAval(){
		BookManag bm = new BookManag();
		if(bm.isAvailable(getIsbn())){
			return true;
		}
		new Message("Not Available!");
		return false;
	}
	
	public boolean addLoan(){
		LoanManag lm = new LoanManag();
		if(lm.numOfLoanById(Integer.parseInt(getCardId())) >= 3){
			new Message("More than 3!");
			return false;
		}
		return lm.addLoan(getIsbn(), Integer.parseInt(getCardId()));
	}


}
