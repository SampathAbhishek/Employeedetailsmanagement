package Database;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

 
public class display extends JFrame implements ActionListener {

    JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
    String ids;
    static JTable table;
    String[] columnNames = {"Name", "Salary", "Phone"};
    String from;
    private JButton btnNewButton_1;
    display() {
        l0 = new JLabel("Fetching Employee Information");
        l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l1 = new JLabel("Select name");
        b1 = new JButton("submit");
        l0.setBounds(100, 50, 350, 40);
        l1.setBounds(75, 110, 75, 20);
        b1.setBounds(150, 150, 150, 20);
        b1.addActionListener(this);
        setTitle("Admin");
        getContentPane().setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(1);
        getContentPane().add(l0);
        getContentPane().add(l1);;
        getContentPane().add(b1);
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
  		  Connection con=DriverManager.getConnection(  
  		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");
            
           String query1 ="select name from employee";
  		  PreparedStatement stmt=con.prepareStatement(query1);   
  		  ResultSet rs = stmt.executeQuery();
            
            
            
            
            
            
            Vector v = new Vector();
            while (rs.next()) {
                ids = rs.getString(1);
                v.add(ids);
            }
            c1 = new JComboBox(v);
            c1.setBounds(150, 110, 150, 20);
            getContentPane().add(c1);
            
            JButton btnNewButton = new JButton("exit");
            btnNewButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		UserLogin a = new UserLogin();
            		a.setVisible(true);
            		dispose();
            	}
            });
            btnNewButton.setBounds(351, 150, 85, 21);
            getContentPane().add(btnNewButton);
            
            btnNewButton_1 = new JButton("DROP");
            btnNewButton_1.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		
            		try {
            		  Class.forName("com.mysql.cj.jdbc.Driver");
            		  Connection con=DriverManager.getConnection(  
            		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");
                      String query1 ="DROP TABLE employee";
            		  PreparedStatement stmt=con.prepareStatement(query1);   
            		  ResultSet rs = stmt.executeQuery();
            	      }
            		catch (Exception q) {

                    }		
            }
            });
            btnNewButton_1.setBounds(351, 110, 85, 21);
            getContentPane().add(btnNewButton_1);
            st.close();
            rs.close();
        } catch (Exception e) {

        }
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            showTableData();
        }
    }

 
    public void showTableData() {
        frame1 = new JFrame("Database Search Result");
        frame1.dispose();
        frame1.getContentPane().setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String) c1.getSelectedItem();
        String uname = "";
        String salary = "";
        String phone = "";
        try {
          
           
          Class.forName("com.mysql.cj.jdbc.Driver");
   		  Connection con=DriverManager.getConnection(  
   		  "jdbc:mysql://localhost:3306/projectdbms","root","abhiSurya@54");
             
          String query1 ="select * from employee where Name='" + from + "'";
   		  PreparedStatement stmt=con.prepareStatement(query1);   
   		  ResultSet rs = stmt.executeQuery();
           
           
           
           
           
            int i = 0;
            if (rs.next()) {
                uname = rs.getString("Name");
                salary = rs.getString("salary");
                phone = rs.getString("phone");
                model.addRow(new Object[]{uname,salary,phone});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");

            } else {
                System.out.println(i + " Records Found");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

        frame1.getContentPane().add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }
    public static void main(String args[]) {
        new display();

    }
}