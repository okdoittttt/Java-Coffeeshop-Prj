import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Pmanage extends JFrame {
	
	// dbConnect�� �ʿ��� �������� ����.
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;

	static private JPanel contentPane;
	static private JTextField txtPno;
	static private JTextField txtPName;
	static private JTextField txtCount;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Pmanage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC7AC\uACE0\uBC88\uD638");
		lblNewLabel.setBounds(108, 59, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel2 = new JLabel("\uC7AC\uACE0\uBA85");
		lblNewLabel2.setBounds(108, 104, 57, 15);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("\uC218\uB7C9");
		lblNewLabel3.setBounds(108, 156, 57, 15);
		contentPane.add(lblNewLabel3);
		
		txtPno = new JTextField();
		txtPno.setBounds(213, 56, 116, 21);
		contentPane.add(txtPno);
		txtPno.setColumns(10);
		
		txtPName = new JTextField();
		txtPName.setColumns(10);
		txtPName.setBounds(213, 101, 116, 21);
		contentPane.add(txtPName);
		
		txtCount = new JTextField();
		txtCount.setColumns(10);
		txtCount.setBounds(213, 153, 116, 21);
		contentPane.add(txtCount);
		
		JLabel lblNewLabel_3 = new JLabel("\uC7AC\uACE0\uAD00\uB9AC");
		lblNewLabel_3.setBounds(185, 13, 110, 15);
		contentPane.add(lblNewLabel_3);
		
		JButton btnSave = new JButton("\uCD94\uAC00");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ��ư�� Ŭ������ �� ������ ���� (DB�� ������ ����)
				dbConnect();
				try {
					query("insert", "insert into inventory values('"+txtPno.getText()+"','"+txtPName.getText()+"','"+txtCount.getText()+"')");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
				System.out.println("���׸� �߰��Ϸ�");
				dbDis();

				txtPno.setText("");
				txtPName.setText("");
				txtCount.setText("");
			}
		});
		btnSave.setBounds(62, 203, 97, 23);
		contentPane.add(btnSave);
		
		JButton btnSearch = new JButton("\uAC80\uC0C9");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// �˻��� DB�� ����� ������ TextFeild�� �ҷ��´�.
				// DB�� �����Ҷ��� ������ �߻��� �� �ֱ� ������ try catch���� ����Ѵ�.
				// �����ϴ� DB���� ������ ������ ������ �߻����� �ʰ� ���ϴ� ������ ��ó�� �� �ִ�.
				dbConnect();
				try {
					query("select", "select * from inventory where PNO = '" + txtPno.getText() +"'");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				try {
					viewData();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				dbDis();
			}
		});
		btnSearch.setBounds(171, 203, 97, 23);
		contentPane.add(btnSearch);
		
		JButton btnCancel = new JButton("\uB3CC\uC544\uAC00\uAE30");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
		btnCancel.setBounds(280, 203, 97, 23);
		contentPane.add(btnCancel);
	}
	
	// dbConnect �޼ҵ�
	public static void dbConnect() {
    	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("����̹� �˻� ����!");        
    	}catch(ClassNotFoundException e){
    		System.err.println("error = " + e);
    	}
        
    	
        url = "jdbc:odbc:coffeeshop";
        conn = null;
        stmt = null;
        rs = null;
        String url = "jdbc:mysql://localhost/coffeeshop";
        String sql = "Select * From inventory";
		try {
         
            conn = DriverManager.getConnection(url,"root","apmsetup");

            stmt = conn.createStatement( );

            rs = stmt.executeQuery(sql);
            
            System.out.println("�����ͺ��̽� ���� ����!");            
         
        }
        catch(Exception e) {
            System.out.println("�����ͺ��̽� ���� ����!");
        }
	}
	
	// query �޼ҵ�
	public static void query(String order, String sql) throws SQLException {	// order : insert | SQL : connect
		if (order == "select") {
			rs = stmt.executeQuery(sql);
		} 
		else {
			stmt.executeUpdate(sql);
		}
	}
	
	// dbDis �żҵ�
	public static void dbDis(){
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			System.out.println("�����ͺ��̽� ���� ����!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// viewData �޼ҵ�
	public static void viewData() throws SQLException {
		if(!rs.next()){	// ã�ƿ� �����Ͱ� ���� ��.
			System.out.println("!rs.next()");
		}
		else{			// ã�ƿ� �����Ͱ� ���� ��.
				System.out.println("rs.next()");
				txtPno.setText(String.valueOf(rs.getString("PNO")));	// grobal ������ ��������� ��� �޼ҵ忡�� ��� ����.
	
				txtPName.setText(rs.getString("PRODUCT"));
					
				txtCount.setText(rs.getString("COUNT"));
		}
	}
}
