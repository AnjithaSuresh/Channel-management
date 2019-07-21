package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Objects;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Container;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;    
public class AddPgmList extends JFrame {

	private JPanel contentPane;
	private JTextField pgmid;
	private JTextField name;
	private JTextField dur;
	private JTextField ty;
	private JTextField edtime;
	private JTextField sttime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPgmList frame = new AddPgmList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	JList list;
	
	/**
	 * Create the frame.
	 */
	public AddPgmList() {
		getContentPane().setLayout(null);
		conn=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheDetails = new JLabel("Enter the details");
		lblEnterTheDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblEnterTheDetails.setBounds(148, 11, 132, 36);
		contentPane.add(lblEnterTheDetails);
		
		JLabel lblNewLabel = new JLabel("Pgm ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(105, 89, 76, 20);
		contentPane.add(lblNewLabel);
		
		pgmid = new JTextField();
		pgmid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		pgmid.setBounds(212, 89, 96, 20);
		contentPane.add(pgmid);
		pgmid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(105, 123, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		name.setBounds(212, 120, 96, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(105, 154, 76, 25);
		contentPane.add(lblNewLabel_2);
		
		dur = new JTextField();
		dur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		dur.setBounds(212, 151, 96, 20);
		contentPane.add(dur);
		dur.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(105, 184, 67, 23);
		contentPane.add(lblNewLabel_3);
		
		ty = new JTextField();
		ty.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ty.setBounds(212, 184, 96, 20);
		contentPane.add(ty);
		ty.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Start time");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(105, 225, 76, 14);
		contentPane.add(lblNewLabel_4);
		
		edtime = new JTextField();
		edtime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		edtime.setBounds(212, 225, 96, 20);
		contentPane.add(edtime);
		edtime.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("End time");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(105, 269, 76, 14);
		contentPane.add(lblNewLabel_5);
		
		sttime = new JTextField();
		sttime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		sttime.setBounds(212, 265, 96, 20);
		contentPane.add(sttime);
		sttime.setColumns(10);
		
		JButton sub = new JButton("Submit");
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into program values (?,?,?,?,?,?)";
					PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1, pgmid.getText());
					pst.setString(2, name.getText());
					pst.setString(3, dur.getText());
					pst.setString(4, ty.getText());
					pst.setString(5, edtime.getText());
					pst.setString(6, sttime.getText());
					
					String query2="select * from program where pgmid=?";
					PreparedStatement pst2= conn.prepareStatement(query2);
					pst2.setString(1, pgmid.getText());
					ResultSet rs= pst2.executeQuery();
					
					if(rs.next()) {
						rs.close();
						pst2.close();
						JOptionPane.showMessageDialog(null, "Program already exist!");
						AddPgmList main = new AddPgmList();    
					    main.setVisible(true);
					    dispose();
						
					}
					else
					{
					pst.execute();
					Object[] obj= list.getSelectedValues();
					for(int i=0; i<obj.length; ++i) {
						String temp="";
						temp=(String) obj[i];
						String query1= "insert into pgmDay values (?,?)";
						PreparedStatement pst1= conn.prepareStatement(query1);
						pst1.setString(1, pgmid.getText());
						pst1.setString(2, temp);
						
						pst1.execute();
						pst1.close();
					}
					
					JOptionPane.showMessageDialog(null, "Data saved");
					pst.close();
					AddPgmList main = new AddPgmList();    

				    main.setVisible(true);
				    dispose();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		sub.setBounds(148, 393, 96, 23);
		contentPane.add(sub);
		
		JLabel lblNewLabel_6 = new JLabel("Choose Days");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(105, 305, 76, 36);
		contentPane.add(lblNewLabel_6);
			
		 
		 
	    
		
		DefaultListModel DLM= new DefaultListModel();
		
		DLM.addElement("Weekdays");
		DLM.addElement("Weekends");
		
		list= new JList();
		list.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBounds(212, 313, 96, 48);
		contentPane.add(list);
		list.setModel(DLM);
		
		
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//JScrollPane jlist= new JScrollPane(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramManager pgm= new ProgramManager();
				pgm.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(362, 9, 89, 23);
		contentPane.add(btnBack);

		
		setTitle("Add new Programs");
		
		
		
	}
}
