package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.business.Borrower;
import com.business.BorrowerManag;

public class DisplayBorrowers extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	         
	JTable jt;
	JLabel id;
	JTextField idInput;
	JLabel name;
	JTextField nameInput;
	JButton jb1;
	JButton jb2;
	JScrollPane sp;

	DisplayBorrowers(){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		id = new JLabel("ID:");
		id.setBounds(10, 10, 80, 25);
		panel.add(id);

		idInput = new JTextField(20);
		idInput.setBounds(50, 10, 160, 25);
		panel.add(idInput);
		
		name = new JLabel("Name:");
		name.setBounds(10, 40, 80, 25);
		panel.add(name);

		nameInput = new JTextField(20);
		nameInput.setBounds(50, 40, 160, 25);
		panel.add(nameInput);
		
		jb1 = new JButton("search");
		jb1.setBounds(250, 10, 100, 25);
		panel.add(jb1);
		
		jb2 = new JButton("new borrower");
		jb2.setBounds(400, 10, 150, 25);
		panel.add(jb2);
		
		
		jt=new JTable();
		String column[]={"CARD_ID","SSN","BNAME", "ADDRESSS", "PHONE"};
		DefaultTableModel contactTableModel = (DefaultTableModel) jt.getModel();
	    contactTableModel.setColumnIdentifiers(column);
		
		
		sp = new JScrollPane(jt);
		sp.setBounds(10, 80, 600, 300);
		panel.add(sp);
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	updateTable();
            }
		});
		
		jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	new UpdateBorrower("");
            }
		});
		
		this.add(panel);
		this.setSize(600,500);
		this.setTitle("Show Borrowers");
		this.setVisible(true);
	}
	
	public String getID() {
        return idInput.getText().trim();
    }
	
	public String getName() {
        return nameInput.getText().trim();
    }
	
	public void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		tableModel.setRowCount(0);
		BorrowerManag bom = new BorrowerManag();
		ArrayList<Borrower> borrowers = bom.searchBorrowers(getID(), getName());
		for(int i = 0; i < borrowers.size(); i++){
			String[] borrower = new String[5];
			borrower[0] = Integer.toString(borrowers.get(i).getCard_id());
			borrower[1] = borrowers.get(i).getSsn();
			borrower[2] = borrowers.get(i).getName();
			borrower[3] = borrowers.get(i).getAddresss();
			borrower[4] = borrowers.get(i).getPhone();
			tableModel.addRow(borrower);
		}
		
		jt.setModel(tableModel);
	}

}
