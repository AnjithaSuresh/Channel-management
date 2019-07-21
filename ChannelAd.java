package project;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelAd extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChannelAd frame = new ChannelAd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTextField adName;
	private JTextField adNameText;
	private JTextField adnametext;
	private JTextField durationtext;
	private JTextField termendtext;
	/**
	 * Create the frame.
	 */
	public ChannelAd() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		adnametext = new JTextField();
		adnametext.setBounds(196, 78, 158, 19);
		contentPane.add(adnametext);
		adnametext.setColumns(10);
		
		durationtext = new JTextField();
		durationtext.setBounds(196, 113, 158, 19);
		contentPane.add(durationtext);
		durationtext.setColumns(10);
		contentPane.setLayout(null);
		
		JLabel lblAdName = new JLabel("Ad name");
		lblAdName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdName.setBounds(52, 75, 105, 21);
		contentPane.add(lblAdName);
		
		JLabel lblNewLabel = new JLabel("Duration");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(52, 107, 120, 26);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Term end year");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(52, 136, 137, 35);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAdd = new JButton("Submit");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "insert into Channelad (adname,duration,termendyear) values (?,?,?)";
					PreparedStatement pat=conn.prepareStatement(query);
					pat.setString(1,adnametext.getText());
					pat.setString(2,durationtext.getText());
					pat.setString(3,termendtext.getText());
					pat.execute();
					
					JOptionPane.showMessageDialog(null, "data added");
					
					pat.close();
					ChannelAd main = new ChannelAd();    

				    main.setVisible(true);
				    dispose();
				}
				catch(Exception e1) {
					//JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(155, 194, 120, 26);
		contentPane.add(btnAdd);
		
		termendtext = new JTextField();
		termendtext.setBounds(196, 144, 158, 20);
		contentPane.add(termendtext);
		termendtext.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdManager ad= new AdManager();
				ad.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(329, 11, 95, 26);
		contentPane.add(btnBack);	
		
		JLabel lblAddChannelAd = new JLabel("Add Channel Ad");
		lblAddChannelAd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		lblAddChannelAd.setBounds(120, 21, 172, 35);
		contentPane.add(lblAddChannelAd);
		
	}
}
