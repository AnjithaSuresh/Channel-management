package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;

public class EditList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditList frame = new EditList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;

	/**
	 * Create the frame.
	 * 
	 */
	public EditList() {
		conn= sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramManager pg = new ProgramManager();
				pg.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(348, 11, 76, 23);
		contentPane.add(btnBack);
		
		JLabel lblEnterTheProgram = new JLabel("Enter the program id");
		lblEnterTheProgram.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterTheProgram.setBounds(42, 46, 136, 23);
		contentPane.add(lblEnterTheProgram);
		
		textField = new JTextField();
		textField.setBounds(235, 48, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGetProgram = new JButton("Get program");
		btnGetProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from program p1 left join pgmDay p2 on p1.pgmid=p2.pgmid  where p1.pgmid=?";
					PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					ResultSet rs= pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
				}catch(Exception e3) {
					e3.printStackTrace();
				}
				
			}
			
		});
		
		btnGetProgram.setBounds(158, 87, 108, 23);
		contentPane.add(btnGetProgram);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 134, 386, 52);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		setTitle("Editing a program");
	}
}
