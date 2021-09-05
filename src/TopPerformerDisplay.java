package TableEntities;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TopPerformerDisplay extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable TopPerformerTable;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private static TopPerformerDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TopPerformerDisplay();
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
	public TopPerformerDisplay() {
		setTitle("League Champions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(57, 10, 520, 31);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Top Players");
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Greatest Players");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Player> players = null;
				try {
					AllTimeHighPerformerDAO playerDAO = new AllTimeHighPerformerDAO();
					players = playerDAO.getAllPlayers();
					
					AllTimeHighTableModel model = new AllTimeHighTableModel(players);
					TopPerformerTable.setModel(model);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TopPerformerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("League Winners");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<LeagueWinner> leagues = null;
				try {
					LeagueWinnerDAO leagueDAO = new LeagueWinnerDAO();
					leagues = leagueDAO.getAllPlayers();
					
					LeagueWinnerTableModel model = new LeagueWinnerTableModel(leagues);
					TopPerformerTable.setModel(model);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TopPerformerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnNewButton_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 62, 652, 303);
		contentPane.add(scrollPane);
		
		TopPerformerTable = new JTable();
		scrollPane.setViewportView(TopPerformerTable);
		
		btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnNewButton_3.setBounds(285, 392, 85, 21);
		contentPane.add(btnNewButton_3);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TopPerformer> players = null;
				try {
					TopPerformer playerDAO = new TopPerformer();
					players = playerDAO.getTopPerformers();
					
					TopPerformerTableModel model = new TopPerformerTableModel(players);
					TopPerformerTable.setModel(model);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TopPerformerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}

}
