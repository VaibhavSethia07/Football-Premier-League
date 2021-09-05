package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class PlayerHigherThanAvgDisplay extends JFrame {

	private JPanel contentPane;
	private JTable highPlayersTable;
	private static PlayerHigherThanAvgDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PlayerHigherThanAvgDisplay();
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
	public PlayerHigherThanAvgDisplay() {
		setTitle("Players with High Average");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(114, 10, 426, 29);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Search by UEFA Team");
		panel.add(lblNewLabel);
		
		JComboBox teamComboBox = new JComboBox();
		teamComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String team = (String)teamComboBox.getSelectedItem();
				if(team=="----------")
					team="";
				List<Player> players = null;
				try {
					PlayerHigherThanAvgDAO playerDAO = new PlayerHigherThanAvgDAO();
					if(team != null && team.trim().length()>0)
						players = playerDAO.searchPlayers(team);
					else
						players = playerDAO.getHighPlayers();
					
					PlayerHigherThanAvgTableModel model = new PlayerHigherThanAvgTableModel(players);
					
					highPlayersTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PlayerHigherThanAvgDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		teamComboBox.setModel(new DefaultComboBoxModel(new String[] {"----------", "Barcelona", "Chelsea", "Real Madrid"}));
		panel.add(teamComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 60, 652, 314);
		contentPane.add(scrollPane);
		
		highPlayersTable = new JTable();
		scrollPane.setViewportView(highPlayersTable);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(282, 392, 85, 21);
		contentPane.add(btnNewButton);
	}

}
