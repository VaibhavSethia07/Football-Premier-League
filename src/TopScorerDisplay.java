package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class TopScorerDisplay extends JFrame {

	private JPanel contentPane;
	private JTable TopScorerTable;
	private JTable TopScorerStatsTable;
	private static TopScorerDisplay frame; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TopScorerDisplay();
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
	public TopScorerDisplay() {
		setTitle("Top Scorer Of Matches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 38);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Top Scorer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TopScorer> players=null;
				try {
					TopScorerDAO playerDAO = new TopScorerDAO();
					
					players = playerDAO.getAllPlayers();
					
					TopScorerTableModel model = new TopScorerTableModel(players);
					
					TopScorerTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TopScorerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Goals Statistics");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TopScorerStats> players=null;
				try {
					TopScorerStatsDAO playerDAO = new TopScorerStatsDAO();
					
					players = playerDAO.getAllStats();
					
					TopScorerStatsTableModel model = new TopScorerStatsTableModel(players);
					
					TopScorerStatsTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TopScorerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 58, 416, 71);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		TopScorerTable = new JTable();
		scrollPane.setViewportView(TopScorerTable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 149, 416, 118);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		TopScorerStatsTable = new JTable();
		scrollPane_1.setViewportView(TopScorerStatsTable);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(171, 289, 85, 21);
		contentPane.add(btnNewButton_2);
	}
}
