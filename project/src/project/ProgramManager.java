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

public class ProgramManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramManager frame = new ProgramManager();
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
	public ProgramManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("logout");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(335, 11, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login log= new login();
				log.frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnBack);
		
		JButton btnAddPgmList = new JButton("Add new pgm list");
		btnAddPgmList.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnAddPgmList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPgmList add = new AddPgmList();
				add.setVisible(true);
				dispose();
				
			}
		});
		btnAddPgmList.setBounds(105, 66, 193, 23);
		contentPane.add(btnAddPgmList);
		
		setTitle("Welcome Program Manager");
		
		JButton btnSchedule = new JButton("Schedule ");
		btnSchedule.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Schedule s= new Schedule();
				s.setVisible(true);
				dispose();
			}
		});
		btnSchedule.setBounds(105, 183, 193, 23);
		contentPane.add(btnSchedule);
		
		JButton btnNewButton = new JButton("Delete pgm from list");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePgm del= new DeletePgm();
				del.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(105, 126, 193, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get program Details");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pgmdetails pg= new pgmdetails();
				pg.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(105, 226, 193, 23);
		contentPane.add(btnNewButton_1);
	}
}
