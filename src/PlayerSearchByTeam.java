package TableEntities;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PlayerSearchByTeam extends JFrame {

	private JPanel contentPane;
	private JTextField TeamtextField;
	private JTextField UEFATeamtextField;
	private JScrollPane scrollPane;
	private JTable PlayerByTeamTable;
	private static PlayerSearchByTeam frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PlayerSearchByTeam();
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
	public PlayerSearchByTeam() {
		setTitle("Player Search By Team");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(111, 5, 426, 31);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Team");
		panel.add(lblNewLabel);
		
		TeamtextField = new JTextField();
		panel.add(TeamtextField);
		TeamtextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UEFA Team");
		panel.add(lblNewLabel_1);
		
		UEFATeamtextField = new JTextField();
		UEFATeamtextField.setText("");
		panel.add(UEFATeamtextField);
		UEFATeamtextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String team = TeamtextField.getText();
				String UEFAteam = UEFATeamtextField.getText();
				List<PlayerByTeam> players = null;
				try {
					PlayerByTeam playerDAO = new PlayerByTeam();
					if(team != null && team.trim().length()>0 && UEFAteam != null && UEFAteam.trim().length()>0)
						players = playerDAO.searchPlayers(team,UEFAteam);
					else
						players = playerDAO.getAllPlayers();
					
					PlayerByTeamTableModel model = new PlayerByTeamTableModel(players);
					
					PlayerByTeamTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PlayerSearchByTeam.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 647, 329);
		contentPane.add(scrollPane);
		
		PlayerByTeamTable = new JTable();
		scrollPane.setViewportView(PlayerByTeamTable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(311, 392, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
