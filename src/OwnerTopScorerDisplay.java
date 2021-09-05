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

public class OwnerTopScorerDisplay extends JFrame {

	private JPanel contentPane;
	private JTable ownerTopScorerTable;
	private static OwnerTopScorerDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new OwnerTopScorerDisplay();
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
	public OwnerTopScorerDisplay() {
		setTitle("Owners of Top Scorers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 692, 31);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Owner of Top Scoring Players");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Owner> owners = null;
				try {
					OwnersTopScorerDAO ownerTopDAO = new OwnersTopScorerDAO();
	
					owners = ownerTopDAO.getAllOwners();
					
					OwnersTopScorerTableModel model = new OwnersTopScorerTableModel(owners);
					
					ownerTopScorerTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(OwnerTopScorerDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 36, 692, 138);
		contentPane.add(scrollPane);
		
		ownerTopScorerTable = new JTable();
		scrollPane.setViewportView(ownerTopScorerTable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(312, 184, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
