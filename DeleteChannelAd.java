package project;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DeleteChannelAd extends JFrame {

	private JPanel contentPane;
	private JTextField nametext;
	//private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteChannelAd frame = new DeleteChannelAd();
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
	public DeleteChannelAd() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadallChannelAds = new JButton("Load all channel ads");
		btnLoadallChannelAds.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLoadallChannelAds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from Channelad order by termendyear";
					PreparedStatement pst= conn.prepareStatement(query);
					ResultSet rs= pst.executeQuery();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLoadallChannelAds.setBounds(415, 29, 189, 28);
		contentPane.add(btnLoadallChannelAds);
		
		JLabel lblEnterAdId = new JLabel("Enter ad name");
		lblEnterAdId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterAdId.setBounds(21, 93, 103, 20);
		contentPane.add(lblEnterAdId);
		
		nametext = new JTextField();
		nametext.setBounds(134, 93, 96, 20);
		contentPane.add(nametext);
		nametext.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if((nametext.getText()).contentEquals(""))
					{	JOptionPane.showMessageDialog(null, "Enter valid ad name");}
					
					else{
						//String query1="select SlNo from program where SlNo=?";
					
				
					String query2= "delete from Channelad where adname=?";
					PreparedStatement pst1= conn.prepareStatement(query2);
					pst1.setString(1, nametext.getText());
					pst1.execute();
					pst1.close();
					
				}}
				 catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			
		});
		btnDelete.setBounds(81, 164, 89, 23);
		contentPane.add(btnDelete);
		
		//table = new JTable();
		//table.setBounds(319, 78, 1, 1);
		//contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 79, 421, 315);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdManager ad= new AdManager();
				ad.setVisible(true);
				dispose();
			}
			
		});
		btnNewButton.setBounds(678, 11, 89, 23);
		contentPane.add(btnNewButton);
	}

}
