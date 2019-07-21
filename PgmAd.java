package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.border.EmptyBorder;


import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PgmAd extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PgmAd frame = new PgmAd();
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
	Connection conn=null;
	private JTextField slnotext;
	private JTextField adnametext;
	private JTextField durationtext;
	private JTextField pgmtext;
	private JTextField priortext;
	public PgmAd() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sl.No");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(55, 108, 94, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(55, 155, 94, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Duration(minutes)");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(55, 204, 160, 19);
		contentPane.add(lblNewLabel_2);
		
		slnotext = new JTextField();
		slnotext.setBounds(225, 107, 112, 20);
		contentPane.add(slnotext);
		slnotext.setColumns(10);
		
		adnametext = new JTextField();
		adnametext.setBounds(225, 155, 112, 19);
		contentPane.add(adnametext);
		adnametext.setColumns(10);
		
		durationtext = new JTextField();
		durationtext.setBounds(225, 204, 112, 19);
		contentPane.add(durationtext);
		durationtext.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Add program ad");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_3.setBounds(127, 33, 225, 41);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Program");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(55, 250, 111, 19);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPriority.setBounds(55, 293, 111, 19);
		contentPane.add(lblPriority);
		
		pgmtext = new JTextField();
		pgmtext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pgmtext.setBounds(225, 251, 112, 19);
		contentPane.add(pgmtext);
		pgmtext.setColumns(10);
		
		priortext = new JTextField();
		priortext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priortext.setBounds(225, 292, 112, 19);
		contentPane.add(priortext);
		priortext.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "insert into PgmAd (SlNo,Adname,duration) values (?,?,?)";
					PreparedStatement pat=conn.prepareStatement(query);
					pat.setString(1,slnotext.getText());
					pat.setString(2,adnametext.getText());
					pat.setString(3,durationtext.getText());
					String query2 = "select * from PgmAd where Adname=? and duration=? and SlNo=?";
					PreparedStatement pat3=conn.prepareStatement(query2);
					
					pat3.setString(1,adnametext.getText());
					pat3.setString(2,durationtext.getText());
					pat3.setString(3,slnotext.getText());
					ResultSet rs = pat3.executeQuery();
					int count =0;
					while(rs.next()) {
						count=count+1;
					}
					if(count == 0) {
					pat.execute();
					}
					String query1 = "insert into SponsPgm(SlNo,Pgm,Priority) values(?,?,?)";
					PreparedStatement pat1=conn.prepareStatement(query1);
					pat1.setString(1,slnotext.getText() );
					pat1.setString(2,pgmtext.getText());
					
					String query4 = "select * from program where name=?";
					PreparedStatement pat4=conn.prepareStatement(query4);
					pat4.setString(1,pgmtext.getText());
					ResultSet rs4 = pat4.executeQuery();
					 count=0;
					 while(rs4.next()) {
							count=count+1;
						}
					if(count ==0) {
						JOptionPane.showMessageDialog(null, "invalid program");
						AdManager main=new AdManager();
						main.setVisible(true);
						dispose();
						
					}
					else {
					pat1.setString(3,priortext.getText());
					pat1.execute();
					
					JOptionPane.showMessageDialog(null, "data added");
					
					pat.close();}
				}
				catch(Exception e1) {
					//JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(144, 338, 85, 31);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdManager ad = new AdManager();
				ad.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(330, 10, 94, 20);
		contentPane.add(btnBack);
		
		
	}

}
