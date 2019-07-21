package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdManager frame = new AdManager();
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
	public AdManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddNewAd = new JButton("Add channel Ad");
		btnAddNewAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChannelAd ad=new ChannelAd();
				ad.setVisible(true);
				dispose();
				
				
			}
		});
		btnAddNewAd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddNewAd.setBounds(88, 53, 229, 27);
		contentPane.add(btnAddNewAd);
		
		JButton btnAddProgramAd = new JButton("Add Program Ad");
		btnAddProgramAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PgmAd ad=new PgmAd();
				ad.setVisible(true);
				dispose();
			}
		});
		btnAddProgramAd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddProgramAd.setBounds(88, 102, 229, 27);
		contentPane.add(btnAddProgramAd);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login log= new login();
				log.frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(352, 11, 89, 23);
		contentPane.add(btnLogout);
		
		setTitle("Welcome Advertisment Manager");
		
		JButton btnDeleteProgramAd = new JButton("Delete Program Ad");
		btnDeleteProgramAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePgmAd ob=new DeletePgmAd();
				ob.setVisible(true);
				dispose();
			}
		});
		btnDeleteProgramAd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDeleteProgramAd.setBounds(88, 150, 229, 27);
		contentPane.add(btnDeleteProgramAd);
		
		JButton btnDeleteProgramAd_1 = new JButton("Delete Channel Ad");
		btnDeleteProgramAd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteChannelAd ob= new DeleteChannelAd();
				ob.setVisible(true);
				dispose();
			}
		});
		btnDeleteProgramAd_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnDeleteProgramAd_1.setBounds(88, 193, 229, 27);
		contentPane.add(btnDeleteProgramAd_1);
		
		JButton btnNewButton = new JButton("Schedule");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Schedule s= new Schedule();
				s.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(88, 237, 229, 27);
		contentPane.add(btnNewButton);
	}
	}


