package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pgmdetails extends JFrame {

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
					pgmdetails frame = new pgmdetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JButton btnLoad;
	private JButton btnBack;
	/**
	 * Create the frame.
	 */
	
	public pgmdetails() {
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterPgmId = new JLabel("Enter pgm id:");
		lblEnterPgmId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterPgmId.setBounds(116, 57, 114, 29);
		contentPane.add(lblEnterPgmId);
		
		textField = new JTextField();
		textField.setBounds(238, 62, 146, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 165, 454, 63);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if((textField.getText()).contentEquals(""))
					{	JOptionPane.showMessageDialog(null, "Enter valid pgmid");}
					
					else{
						String query1="select * from program where pgmid=?";
				
						PreparedStatement pst1= conn.prepareStatement(query1);
						pst1.setString(1, textField.getText());
					
						ResultSet rs1= pst1.executeQuery();
						
						if(!rs1.next()) {
							JOptionPane.showMessageDialog(null, "Enter valid pgmid");
							rs1.close();
							pst1.close();
						}
						else {
							String q2= "select p.pgmid, name, duration, type, start_time, end_time, day from program p inner join pgmday pd on p.pgmid=pd.pgmid where p.pgmid=?";
							PreparedStatement pst2= conn.prepareStatement(q2);
							pst2.setString(1, textField.getText());
							ResultSet rs2= pst2.executeQuery();
							

							table.setModel(DbUtils.resultSetToTableModel(rs2));
							
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLoad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnLoad.setBounds(198, 108, 89, 23);
		contentPane.add(btnLoad);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramManager pg = new ProgramManager();
				pg.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(402, 11, 75, 20);
		contentPane.add(btnBack);
	}
}
