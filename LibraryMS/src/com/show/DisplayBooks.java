package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.business.*;

public class DisplayBooks extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	         
	JTable jt;
	JLabel lable;
	JTextField sText;
	JButton jb1;
	JButton jb2;
	JScrollPane sp;

	DisplayBooks(){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		lable = new JLabel("Key words:");
		lable.setBounds(10, 10, 80, 25);
		panel.add(lable);

		sText = new JTextField(20);
		sText.setBounds(100, 10, 160, 25);
		panel.add(sText);
		
		jb1 = new JButton("search");
		jb1.setBounds(300, 10, 100, 25);
		panel.add(jb1);
		
		jb2 = new JButton("Check Out");
		jb2.setBounds(450, 10, 100, 25);
		panel.add(jb2);
		
		jt=new JTable();
		String column[]={"ISBN","TITLE","AUTHOR", "AVAILABLE"};
		DefaultTableModel contactTableModel = (DefaultTableModel) jt.getModel();
	    contactTableModel.setColumnIdentifiers(column);
		
		
		sp = new JScrollPane(jt);
		sp.setBounds(10, 50, 500, 300);
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
            	generateLoan();
            }
		});
		
		this.add(panel);
		this.setSize(600,500);
		this.setTitle("Search Books");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        //this.pack();
		this.setVisible(true);
	}
	
	public String getKeyWord() {
        return sText.getText().trim();
    }
	
	public void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		tableModel.setRowCount(0);
		BookManag bm = new BookManag();
		ArrayList<Book> books = bm.searchBooks(getKeyWord());
		for(int i = 0; i < books.size(); i++){
			String[] book = new String[4];
			book[0] = books.get(i).getIsbn();
			book[1] = books.get(i).getTitle();
			book[2] = books.get(i).getAuthor();
			book[3] = books.get(i).getAvail().toString();
			tableModel.addRow(book);
		}
		
		jt.setModel(tableModel);
	}
	
	public void generateLoan(){
		int row = jt.getSelectedRow();
		if(row < 0){
			new Message("Please select a book!");
			return;
		}
		if(jt.getModel().getValueAt(row, 0).toString().equals("false")){
			new Message("Not available!");
		}
		String value = jt.getModel().getValueAt(row, 0).toString();
		new CheckOut(value);
	}

}
