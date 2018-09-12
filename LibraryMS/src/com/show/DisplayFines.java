package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.business.*;

public class DisplayFines extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	         
	JTable jt;
	
	JButton jb1;
	JScrollPane sp;

	DisplayFines(){
		
		initialFines();
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		jb1 = new JButton("Pay it");
		jb1.setBounds(10, 10, 150, 25);
		panel.add(jb1);
		
		
		jt=new JTable();
		String column[]={"Card_ID", "Amount"};
		DefaultTableModel contactTableModel = (DefaultTableModel) jt.getModel();
	    contactTableModel.setColumnIdentifiers(column);
		
		
		sp = new JScrollPane(jt);
		sp.setBounds(10, 50, 600, 300);
		panel.add(sp);
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	payFine();
            	updateTable();
            }
		});
		
		
		this.add(panel);
		this.setSize(600,500);
		this.setTitle("Display Fines By User");
		this.setVisible(true);
		
		updateTable();
	}
	
	private void initialFines(){
		LoanManag lm = new LoanManag();
		ArrayList<Loan> dueloans = lm.listDueLoans();
		for(Loan loan : dueloans){
			lm.updateFine(loan.getLoanid());
		}
	}
	
	private void payFine(){
		FineManag fm = new FineManag();
		LoanManag lm = new LoanManag();
		int row = jt.getSelectedRow();
		
		if(row < 0){
			new Message("Please select a book!");
			return;
		}
		String value = jt.getModel().getValueAt(row, 0).toString();
		int cardid = Integer.parseInt(value);
		if(!lm.isReturnByUID(cardid)){
			new Message("Please return books!");
			return;
		}
		fm.updatePay(cardid);
	}
	
	private void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		tableModel.setRowCount(0);
		FineManag fm = new FineManag();
		ArrayList<FineByUser> fines = fm.listFineByUser();
		for(int i = 0; i < fines.size(); i++){
			String[] fine = new String[2];
			fine[0] = "" + fines.get(i).getCardid();
			fine[1] = "" + fines.get(i).getAmount();
			
			tableModel.addRow(fine);
		}
		
		jt.setModel(tableModel);
	}

}
