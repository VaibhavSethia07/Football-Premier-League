package TableEntities;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class PlayerSearchByOwner extends JFrame {

	private JPanel contentPane;
	private JTextField OwnerFnametextField;
	private JTable PlayerTable;
	private static PlayerSearchByOwner frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PlayerSearchByOwner();
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
	public PlayerSearchByOwner() {
		setTitle("Player Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(131, 10, 426, 31);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Enter Owner's Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		OwnerFnametextField = new JTextField();
		OwnerFnametextField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(OwnerFnametextField);
		OwnerFnametextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = OwnerFnametextField.getText();
				List<Player> players = null;
				try {
					PlayerDAO playerDAO = new PlayerDAO();
					if(firstName != null && firstName.trim().length()>0)
						players = playerDAO.searchPlayers(firstName);
					else
						players = playerDAO.getAllPlayers();
					
					PlayerTableModel model = new PlayerTableModel(players);
					
					PlayerTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PlayerSearchByOwner.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 52, 652, 324);
		contentPane.add(scrollPane);
		
		PlayerTable = new JTable();
		scrollPane.setViewportView(PlayerTable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(303, 392, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
