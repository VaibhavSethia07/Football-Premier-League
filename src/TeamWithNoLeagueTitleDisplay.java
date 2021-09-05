package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class TeamWithNoLeagueTitleDisplay extends JFrame {

	private JPanel contentPane;
	private JTable TeamWithNoTitletable;
	static TeamWithNoLeagueTitleDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TeamWithNoLeagueTitleDisplay();
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
	public TeamWithNoLeagueTitleDisplay() {
		setTitle("Teams with no title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(48, 16, 426, 31);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Teams with no League Title");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TeamWithNoLeagueTitle> teams = null;
				try {
					TeamWithNoLeagueTitleDAO teamDAO = new TeamWithNoLeagueTitleDAO();
					
					teams = teamDAO.getAllTeams();

					TeamWithNoLeagueTitleModel model = new TeamWithNoLeagueTitleModel(teams);
					
					TeamWithNoTitletable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TeamWithNoLeagueTitleDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 57, 552, 261);
		contentPane.add(scrollPane);
		
		TeamWithNoTitletable = new JTable();
		scrollPane.setViewportView(TeamWithNoTitletable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(233, 328, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
