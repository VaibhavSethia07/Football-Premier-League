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

public class TeamsHighPerformanceBallersDisplay extends JFrame {

	private JPanel contentPane;
	private JTable TeamPerformancetable;
	private static TeamsHighPerformanceBallersDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TeamsHighPerformanceBallersDisplay();
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
	public TeamsHighPerformanceBallersDisplay() {
		setTitle("Team Performance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(111, 10, 426, 31);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Team Performance in Leagues");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TeamsHighPerformanceBallers> teams = null;
				try {
					TeamsHighPerformanceBallersDAO teamDAO = new TeamsHighPerformanceBallersDAO();
					
					teams = teamDAO.getAllTeams();

					TeamsHighPerformanceBallersModel model = new TeamsHighPerformanceBallersModel(teams);
					
					TeamPerformancetable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(TeamsHighPerformanceBallersDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 58, 652, 324);
		contentPane.add(scrollPane);
		
		TeamPerformancetable = new JTable();
		scrollPane.setViewportView(TeamPerformancetable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnNewButton_1.setBounds(288, 392, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
