package Database;

//package com.javaguides.javaswing.reg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class UserRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JTextField idtext;
    private JButton btnNewButton_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserRegistration frame = new UserRegistration();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public UserRegistration() {
        //setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("User Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        lblNewUserRegister.setBounds(152, 38, 228, 50);
        contentPane.add(lblNewUserRegister);

        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstname.setBounds(214, 172, 261, 36);
        contentPane.add(firstname);
        firstname.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(67, 166, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(67, 240, 99, 24);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(214, 237, 261, 36);
        contentPane.add(passwordField);

        btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = firstname.getText();
				String password = passwordField.getText();

                try {
                	Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdbms","root", "abhiSurya@54");

                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("insert into userreg values(?,?,?)");
		    		int  s1 = Integer.parseInt(idtext.getText());
		    		String s2 = firstname.getText();
		    		String s3 = passwordField.getText();
		    		st.setInt(1, s1);
		    		st.setString(2, s2);
		    		st.setString(3, s3);
		    		System.out.print(s1+s2+s3);
		    		int i = st.executeUpdate();
		    		System.out.println(i+"row's affected");
                    connection.close();
                } catch (Exception exception) {
                    
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(46, 372, 200, 36);
        contentPane.add(btnNewButton);
        
        JLabel id = new JLabel("enumber");
        id.setFont(new Font("Tahoma", Font.PLAIN, 20));
        id.setBounds(67, 310, 99, 26);
        contentPane.add(id);
        
        idtext = new JTextField();
        idtext.setBounds(214, 307, 261, 29);
        contentPane.add(idtext);
        idtext.setColumns(10);
        
        btnNewButton_1 = new JButton("exit");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_1.setBounds(282, 372, 152, 36);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Back");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UserLogin a = new UserLogin();
        		a.setVisible(true);
        		dispose();
        	}
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton_2.setBounds(461, 372, 119, 31);
        contentPane.add(btnNewButton_2);
    }
}