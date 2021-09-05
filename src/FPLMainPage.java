package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FPLMainPage extends JFrame {

	private JPanel contentPane;
	public static FPLMainPage mainFrame = new FPLMainPage();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					mainFrame = new FPLMainPage();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static FPLMainPage getInstance() {
		return mainFrame;
	}
	/**
	 * Create the frame.
	 */
	public FPLMainPage() {
		setTitle("Football Premier League");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 42, 628, 450);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 10, 610, 129);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 591, 110);
		panel_5.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Player Search by Team");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerSearchByTeam obj = new PlayerSearchByTeam();
				mainFrame.setVisible(false);
				obj.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Player Search by Owner");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerSearchByOwner obj = new PlayerSearchByOwner();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("League Champions");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TopPerformerDisplay obj = new TopPerformerDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_1.add(btnNewButton_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(10, 156, 610, 129);
		panel_4.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 592, 110);
		panel_6.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Top Scorers Stats & Goals");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TopScorerDisplay obj = new TopScorerDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Players with High Average");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerHigherThanAvgDisplay obj = new PlayerHigherThanAvgDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("League Statistics");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamsHighPerformanceBallersDisplay obj = new TeamsHighPerformanceBallersDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_2.add(btnNewButton_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(10, 295, 610, 134);
		panel_4.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 10, 588, 114);
		panel_7.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_6 = new JButton("Teams With No League Title");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamsHighPerformanceBallersDisplay obj = new TeamsHighPerformanceBallersDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panel_3.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Teams with High Win/Loss Gap");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighGapDisplay obj = new HighGapDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_7.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel_3.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Owners of MVP ");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnerTopScorerDisplay obj = new OwnerTopScorerDisplay();
				obj.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_3.add(btnNewButton_8);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("FOOTBALL PREMIER LEAGUE");
		lblNewJgoodiesTitle.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewJgoodiesTitle.setBounds(31, 10, 591, 31);
		contentPane.add(lblNewJgoodiesTitle);
		lblNewJgoodiesTitle.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
