package project;


import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DeletePgmAd extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePgmAd frame = new DeletePgmAd();
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
	private JScrollPane scrollPane;
	private JButton btnBack;
	public DeletePgmAd() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdId = new JLabel("Enter the Ad ID");
		lblAdId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAdId.setBounds(36, 126, 112, 14);
		contentPane.add(lblAdId);
		
		idtext = new JTextField();
		idtext.setBounds(147, 124, 96, 20);
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if((idtext.getText()).contentEquals(""))
				{	JOptionPane.showMessageDialog(null, "Enter valid ad id");}
				
				else{
					//String query1="select SlNo from program where SlNo=?";
				
			
				String query2= "delete from PgmAd where SlNo=?";
				PreparedStatement pst1= conn.prepareStatement(query2);
				pst1.setString(1, idtext.getText());
				pst1.execute();
				pst1.close();
				String query1= "delete from SponsPgm where SlNo=?";
				PreparedStatement pst2= conn.prepareStatement(query1);
				pst2.setString(1, idtext.getText());
				pst2.execute();
				pst2.close();
			}}
			 catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(59, 180, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnLoadProgramAd = new JButton("Load program ad data");
		btnLoadProgramAd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLoadProgramAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query= "select * from PgmAd order by SlNo";
					PreparedStatement pst= conn.prepareStatement(query);
					ResultSet rs= pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLoadProgramAd.setBounds(418, 37, 214, 23);
		contentPane.add(btnLoadProgramAd);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(284, 88, 478, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdManager ad= new AdManager();
				ad.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(715, 11, 89, 23);
		contentPane.add(btnBack);
	}

}
