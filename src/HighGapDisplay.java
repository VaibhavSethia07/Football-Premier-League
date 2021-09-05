package TableEntities;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HighGapDisplay extends JFrame {

	private JPanel contentPane;
	private JTable teamGapTable;
	private JTable TeamMaxGaptable;
	private static HighGapDisplay frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HighGapDisplay();
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
	public HighGapDisplay() {
		setTitle("Team Performance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 599, 406);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 589, 80);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(206, 33, 151, 41);
		panel_3.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("No. of Teams");
		panel_2.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		panel_2.add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int limitValue = (Integer)spinner.getValue();
				
				List<HighGapStats> gapStats = null;
				try {
					HighGapStatsDAO gapStatsDAO = new HighGapStatsDAO();
	
					gapStats = gapStatsDAO.topGapStats(limitValue);
					
					HighGapStatsTableModel model = new HighGapStatsTableModel(gapStats);
					
					teamGapTable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(HighGapDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JLabel lblNewLabel = new JLabel("Top Teams with highest gaps");
		lblNewLabel.setBounds(188, 0, 175, 29);
		panel_3.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 83, 579, 313);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		teamGapTable = new JTable();
		scrollPane.setViewportView(teamGapTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(619, 58, 341, 156);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Teams Max Score");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<HighGap> gap = null;
				try {
					HighGapDAO gapDAO = new HighGapDAO();
	
					gap = gapDAO.getAllHighGap();
					
					HighGapTableModel model = new HighGapTableModel(gap);
					
					TeamMaxGaptable.setModel(model);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(HighGapDisplay.this, "Error: "+e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		panel_5.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		TeamMaxGaptable = new JTable();
		scrollPane_1.setViewportView(TeamMaxGaptable);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FPLMainPage obj = FPLMainPage.getInstance();
				obj.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnNewButton_1.setBounds(748, 235, 85, 21);
		contentPane.add(btnNewButton_1);
	}

}
