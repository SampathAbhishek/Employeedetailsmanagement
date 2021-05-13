package Database;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.awt.Font;
import java.sql.*;
class design extends JFrame 
{
  
	private static final long serialVersionUID = 1L;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTable table;
	DefaultTableModel model;
	public design()
	{
		setVisible(true);
		setDefaultCloseOperation(3);
		getContentPane().setLayout(null);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		JLabel p1 = new JLabel("Employee Details Storage System");
		p1.setFont(new Font("Tahoma", Font.BOLD, 18));
		p1.setBounds(261, 23, 448, 31);
		getContentPane().add(p1);
		
		JLabel l1 = new JLabel("Name");
		l1.setFont(new Font("Verdana", Font.PLAIN, 18));
		l1.setBounds(114, 86, 104, 31);
		getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Salary");
		l2.setFont(new Font("Verdana", Font.PLAIN, 18));
		l2.setBounds(114, 127, 104, 26);
		getContentPane().add(l2);
		
		JLabel l3 = new JLabel("Number");
		l3.setFont(new Font("Verdana", Font.PLAIN, 18));
		l3.setBounds(114, 163, 104, 26);
		getContentPane().add(l3);
		
		t1 = new JTextField(15);
		t1.setBounds(274, 97, 151, 19);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField(15);
		t2.setBounds(274, 136, 151, 19);
		getContentPane().add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField(15);
		t3.setBounds(274, 172, 151, 19);
		getContentPane().add(t3);
		t3.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 281, 564, 180);
		getContentPane().add(scrollPane);
		
		//creation of table--->very important.
		table = new JTable();
		model = new DefaultTableModel();
		Object [] cloumn = {"Name","Salary","Number"};
		final Object [] row = new Object[3];
		model.setColumnIdentifiers(cloumn);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
		JButton b1 = new JButton("Add ");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
				}
				else
				{
				row[0] = t1.getText();
				row[1] = t2.getText();
				row[2] = t3.getText();
				model.addRow(row);
				try{  
		    		  
		    		  Class.forName("com.mysql.cj.jdbc.Driver");
		    		  Connection con=DriverManager.getConnection(  
		    		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");  
		    		  String query1 ="insert into employee values(?,?,?)";
		    		  String query2 ="insert into groupinfo values (?,?)";
		    		  PreparedStatement stmt=con.prepareStatement(query1);
		    		  PreparedStatement stmt2=con.prepareStatement(query2);
		    		  String s1 = t1.getText();
		    		  int s2 = Integer.parseInt(t2.getText());
		    		  int s3 = Integer.parseInt(t3.getText());
		    		  stmt.setString(1, s1);
		    		  stmt.setInt(2, s2);
		    		  stmt.setInt(3, s3);
		    		  stmt2.setString(1,s1);
		    		  Random random = new Random();
		    		  int p = random.nextInt(20);
		    		  stmt2.setInt(2,p);
		    		  int i = stmt.executeUpdate();
		    		  int j = stmt2.executeUpdate();
		    		  System.out.println(i+"row's affected");
		    		  System.out.println(j+"row's affected");
		    		  con.close();  
		    		  }
		    	  catch(Exception e1)
		    	  { 
		    		  System.out.println(e1);
		    	  }  
				
				t1.setText("");
				t2.setText("");
				t3.setText("");
				JOptionPane.showMessageDialog(null, "Saved Successfully");
				}
			}
		});
		b1.setBounds(139, 235, 64, 21);
		getContentPane().add(b1);
		
		JButton b2 = new JButton("Update");
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				t1.setText(model.getValueAt(i,0).toString());
				t2.setText(model.getValueAt(i,1).toString());
				t3.setText(model.getValueAt(i,2).toString());
				JOptionPane.showMessageDialog(null, "Updated successfully");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>=0)
				{
				model.setValueAt(t1.getText(),i,0);
				model.setValueAt(t2.getText(),i,1);
				model.setValueAt(t3.getText(),i,2);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select the row to update");
				}
			}
		});
		b2.setBounds(274, 235, 83, 21);
		getContentPane().add(b2);
		
		JButton b3 = new JButton("Delete");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>=0) 
				{
				
				try{  
					
		    		  Class.forName("com.mysql.cj.jdbc.Driver");
		    		  Connection con=DriverManager.getConnection(  
		    		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");  
		    		  String query1 = "delete from groupinfo where Name=?";
		    		  String query2 ="delete from employee where Name=?";
		    		  PreparedStatement stmt=con.prepareStatement(query1); 
		    		  PreparedStatement stmt2=con.prepareStatement(query2);
                      String name = table.getValueAt(i, 0).toString(); 
		    		  stmt.setString(1,name);
		    		  stmt2.setString(1,name);
		    		  int j=stmt.executeUpdate();
		    		  int k=stmt2.executeUpdate();
		    		  con.close();  
		    		  model.removeRow(i);
		    		  }
		    	  catch(Exception e1)
		    	  { 
		    		  JOptionPane.showMessageDialog(null,e1.getMessage());
		    	  }  
				
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Select a row first");
				}
			}
		});
		b3.setBounds(416, 235, 85, 21);
		getContentPane().add(b3);
		
		JButton b4 = new JButton("Clear");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
			
			}
		});
		b4.setBounds(558, 235, 85, 21);
		getContentPane().add(b4);
		
		JButton c = new JButton("count");
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{   
		    		  Class.forName("com.mysql.cj.jdbc.Driver");
		    		  Connection con=DriverManager.getConnection(  
		    		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");  
		    		  String query1 ="select SUM(Salary)  from employee";
		    		  PreparedStatement stmt=con.prepareStatement(query1);   
		    		  ResultSet rs = stmt.executeQuery();
		    		  rs.next();
		    		  String l = rs.getString("SUM(Salary)");
		    		  JOptionPane.showMessageDialog(null,"The total salary of all employees is:"+l);
		    		  con.close();  
		    		  }
		    	  catch(Exception e1)
		    	  { 
		    		  System.out.println(e1);
		    	  }  
			}
		});
		c.setBounds(515, 134, 85, 21);
		getContentPane().add(c);
		
		JButton btnNewButton = new JButton("exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLogin a = new UserLogin();
        		a.setVisible(true);
        		dispose();
			}
		});
		btnNewButton.setBounds(704, 235, 85, 21);
		getContentPane().add(btnNewButton);
		
		JButton r1 = new JButton("group(naturaljoin)");
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{   
		    		  Class.forName("com.mysql.cj.jdbc.Driver");
		    		  Connection con=DriverManager.getConnection(  
		    		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");  
		    		  String query1 ="select Name,groupid from employee NATURAL JOIN groupinfo where salary > 75000";
		    		  PreparedStatement stmt=con.prepareStatement(query1);   
		    		  ResultSet rs = stmt.executeQuery();
		    		  rs.next();
		    		  String l = rs.getString("Name");
		    		  String m = rs.getString("groupid");
		    		  JOptionPane.showMessageDialog(null,"Name: "+l+"\n"+"Groupid: "+m);
		    		  con.close();  
		    		  }
		    	  catch(Exception e1)
		    	  { 
		    		  System.out.println(e1);
		    	  }  
			}
		});
		r1.setBounds(664, 135, 140, 21);
		getContentPane().add(r1);
		
	}	
	public static void main (String [] args)
	{
		 new  design();
	}
}
