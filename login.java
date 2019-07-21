package project;
import java.sql.*;
import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;

public class login {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField text1;
	private JPasswordField password;

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
		conn= sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 519, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lab1 = new JLabel("Username");
		lab1.setBounds(127, 104, 96, 14);
		lab1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lab2 = new JLabel("Password");
		lab2.setBounds(127, 139, 72, 20);
		lab2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		text1 = new JTextField();
		text1.setBounds(233, 101, 127, 20);
		text1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		text1.setColumns(10);
		
		JButton button1 = new JButton("Login");
		button1.setBounds(200, 199, 89, 23);
		button1.setBackground(Color.LIGHT_GRAY);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from login where username=? and password=?";
					PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1, text1.getText());
					pst.setString(2, password.getText());
					int cnt=0;
					ResultSet rs= pst.executeQuery();
					while(rs.next()) {
						cnt=cnt+1;
					}
					if(cnt==1) {
						
						if(text1.getText().contentEquals("pgmmanager"))
						{
							frame.dispose();
							ProgramManager pgmngr= new ProgramManager();
							pgmngr.setVisible(true);
						}
						else
						{
							frame.dispose();
							AdManager admngr= new AdManager();
							admngr.setVisible(true);
							
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "incorrect login,\nTry again");
						login log= new login();
						log.frame.setVisible(true);
						frame.dispose();
					}
					rs.close();
					pst.close();
				}
			
				
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
					}
		});
		button1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		password = new JPasswordField();
		password.setBounds(233, 141, 127, 20);
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lab1);
		frame.getContentPane().add(lab2);
		frame.getContentPane().add(text1);
		frame.getContentPane().add(button1);
		frame.getContentPane().add(password);
		
		frame.setTitle("Login page");
	}
}
