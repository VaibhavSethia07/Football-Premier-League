package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PlayerStats extends JFrame {

	private JPanel contentPane;
	private JTextField GoldenBoottextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerStats frame = new PlayerStats();
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
	public PlayerStats() {
		setTitle("League Stats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 578, 10);
		contentPane.add(panel);
		
		GoldenBoottextField = new JTextField();
		GoldenBoottextField.setBounds(159, 23, 147, 30);
		contentPane.add(GoldenBoottextField);
		GoldenBoottextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Show Teams");
		btnNewButton.setBounds(5, 346, 162, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PlayerStatsDAO dao = new PlayerStatsDAO();

					String playerName = dao.getGoldenBootPlayer();
					GoldenBoottextField.setText(playerName);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PlayerStats.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
					try {
						TopTeamsDAO dao = new TopTeamsDAO();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					int value = spinner.getValue();
//					System.out.print(value);
//					DefaultListModel model = new DefaultListModel(); 
//					List<String> list = dao.getTeams(value);
//					for(String team: list)
//							model.addElement(team);
//					
//					TopTeamslist.setModel(model);
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Man with Golden Boot");
		lblNewLabel.setBounds(15, 25, 134, 28);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(101, 156, 2, 2);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Matches");
		lblNewLabel_1.setBounds(10, 73, 62, 13);
		contentPane.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(101, 70, 30, 20);
		contentPane.add(spinner);
		
		JList TopTeamslist = new JList();
		TopTeamslist.setBounds(5, 111, 162, 221);
		contentPane.add(TopTeamslist);
	}
}
