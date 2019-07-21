package project;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class DeletePgm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePgm frame = new DeletePgm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public DeletePgm() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 456);
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
		btnBack.setBounds(693, 11, 71, 23);
		contentPane.add(btnBack);
		
		JLabel lblEnterThePgm = new JLabel("Enter the pgm id to be deleted");
		lblEnterThePgm.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterThePgm.setBounds(10, 110, 176, 36);
		contentPane.add(lblEnterThePgm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 94, 568, 312);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if((textField.getText()).contentEquals(""))
					{	JOptionPane.showMessageDialog(null, "Enter valid pgmid");}
					
					else{
						String query1="select name from program where pgmid=?";
				
						PreparedStatement pst1= conn.prepareStatement(query1);
						pst1.setString(1, textField.getText());
					
						ResultSet rs1= pst1.executeQuery();
						
						if(!rs1.next()) {
							JOptionPane.showMessageDialog(null, "Enter valid pgmid");
							rs1.close();
							pst1.close();
						}
						else {
						String name1;
						name1=rs1.getString("name");
						rs1.close();
						pst1.close();
					
						String query2= "delete from SponsPgm where Pgm=?";
						PreparedStatement pst2= conn.prepareStatement(query2);
						pst2.setString(1, name1);
						pst2.execute();
					
						String query= "delete from program where pgmid=?";
						PreparedStatement pst= conn.prepareStatement(query);
						pst.setString(1, textField.getText());
						pst.execute();
						
						String q= "delete from pgmday where pgmid=?";
						PreparedStatement pst0= conn.prepareStatement(q);
						pst0.setString(1, textField.getText());
						pst0.execute();
												
					pst0.close();
					pst.close();
					pst1.close();
					pst2.close();
					
					String query3= "select Slno from PgmAd";
					PreparedStatement pst3=conn.prepareStatement(query3);
					ResultSet rs3= pst3.executeQuery();
					
					String sl;
					while(rs3.next()) {
						sl=rs3.getString("Slno");
						
						String query4="select * from SponsPgm where Slno=?";
						PreparedStatement pst4= conn.prepareStatement(query4);
						pst4.setString(1, sl);
						ResultSet rs4= pst4.executeQuery();
						
						if(!rs4.next()) {
							String query5= "delete from PgmAd where Slno=?";
							PreparedStatement pst5= conn.prepareStatement(query5);
							pst5.setString(1, sl);
							pst5.execute();
							pst5.close();
						}
						
						rs4.close();
						pst4.close();
					}
					
					rs3.close();
					pst3.close();
				
					}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setBounds(43, 216, 96, 23);
		contentPane.add(btnDelete);
		
		JButton btnLoadAllPrograms = new JButton("Load all programs");
		btnLoadAllPrograms.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLoadAllPrograms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from program order by pgmid";
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
		btnLoadAllPrograms.setBounds(365, 53, 284, 30);
		contentPane.add(btnLoadAllPrograms);
		
		textField = new JTextField();
		textField.setBounds(43, 158, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		setTitle("Deleting Programs");
	}
}
