import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC7AC\uACE0\uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		lblNewLabel.setBounds(169, 10, 109, 15);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 410, 91);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "milk", "14"},
				{"2", "coffeebeans", "5"},
				{"3", "syrup", "3"},
				{"4", "bread", "10"},
			},
			new String[] {
				"Product No", "Name", "Count"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton pmanage = new JButton("\uC7AC\uACE0\uAD00\uB9AC");
		pmanage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pmanage frame = new Pmanage();
				frame.setVisible(true);
			}
		});
		pmanage.setBounds(54, 209, 97, 23);
		contentPane.add(pmanage);
		
		JButton omanage = new JButton("\uC8FC\uBB38\uAD00\uB9AC");
		omanage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Omanage frame = new Omanage();
				frame.setVisible(true);
			}
			
		});
		omanage.setBounds(163, 209, 97, 23);
		contentPane.add(omanage);
		
		JButton partnermanage = new JButton("\uAC70\uB798\uCC98\uAD00\uB9AC");
		partnermanage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PartnerManage frame = new PartnerManage();
				frame.setVisible(true);
			}
		});
		partnermanage.setBounds(272, 209, 97, 23);
		contentPane.add(partnermanage);
	}
}
