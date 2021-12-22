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

public class Omanage extends JFrame {
	
	// dbConnect에 필요한 전역번수 선언.
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;

	static private JPanel contentPane;
	static private JTextField txtPno;
	static private JTextField txtPName;
	static private JTextField txtCount;
	static private JTextField txtDate;
	static private JTextField txtPrice;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Omanage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC7AC\uACE0\uBC88\uD638");
		lblNewLabel.setBounds(108, 56, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel2 = new JLabel("\uC7AC\uACE0\uBA85");
		lblNewLabel2.setBounds(108, 84, 57, 15);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("\uC218\uB7C9");
		lblNewLabel3.setBounds(108, 115, 57, 15);
		contentPane.add(lblNewLabel3);
		
		txtPno = new JTextField();
		txtPno.setBounds(213, 53, 116, 21);
		contentPane.add(txtPno);
		txtPno.setColumns(10);
		
		txtPName = new JTextField();
		txtPName.setColumns(10);
		txtPName.setBounds(213, 81, 116, 21);
		contentPane.add(txtPName);
		
		txtCount = new JTextField();
		txtCount.setColumns(10);
		txtCount.setBounds(213, 112, 116, 21);
		contentPane.add(txtCount);
		
		JLabel lblNewLabel_3 = new JLabel("\uC8FC\uBB38\uAD00\uB9AC");
		lblNewLabel_3.setBounds(185, 13, 110, 15);
		contentPane.add(lblNewLabel_3);
		
		JButton btnSave = new JButton("\uCD94\uAC00");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 저장 버튼을 클릭했을 때 동작할 내용 (DB에 내용을 저장)
				dbConnect();
				try {
					query("insert", "insert into order values('"+txtPno.getText()+"','"+txtPName.getText()+"','"+txtCount.getText()+"','"+txtDate.getText()+"','"+txtPrice.getText()+"')");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
				System.out.println("새항목 추가완료");
				dbDis();

				txtPno.setText("");
				txtPName.setText("");
				txtCount.setText("");
				txtDate.setText("");
				txtPrice.setText("");
			}
		});
		btnSave.setBounds(108, 213, 97, 23);
		contentPane.add(btnSave);
		
		JButton btnSearch = new JButton("\uB3CC\uC544\uAC00\uAE30");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
		btnSearch.setBounds(217, 213, 97, 23);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel3_1 = new JLabel("\uCD94\uAC00\uB0A0\uC9DC");
		lblNewLabel3_1.setBounds(108, 143, 57, 15);
		contentPane.add(lblNewLabel3_1);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(213, 140, 116, 21);
		contentPane.add(txtDate);
		
		JLabel lblNewLabel3_2 = new JLabel("\uAC00\uACA9");
		lblNewLabel3_2.setBounds(108, 173, 57, 15);
		contentPane.add(lblNewLabel3_2);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(213, 170, 116, 21);
		contentPane.add(txtPrice);
	}
	
	// dbConnect 메소드
	public static void dbConnect() {
    	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("드라이버 검색 성공!");        
    	}catch(ClassNotFoundException e){
    		System.err.println("error = " + e);
    	}
        
    	
        url = "jdbc:odbc:coffeeshop";
        conn = null;
        stmt = null;
        rs = null;
        String url = "jdbc:mysql://localhost/coffeeshop";
        String sql = "Select * From order";
		try {
         
            conn = DriverManager.getConnection(url,"root","apmsetup");

            stmt = conn.createStatement( );

            rs = stmt.executeQuery(sql);
            
            System.out.println("데이터베이스 연결 성공!");            
         
        }
        catch(Exception e) {
            System.out.println("데이터베이스 연결 실패!");
        }
	}
	
	// query 메소드
	public static void query(String order, String sql) throws SQLException {	// order : insert | SQL : connect
		if (order == "select") {
			rs = stmt.executeQuery(sql);
		} 
		else {
			stmt.executeUpdate(sql);
		}
	}
	
	// dbDis 매소드
	public static void dbDis(){
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			System.out.println("데이터베이스 연결 해제!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// viewData 메소드
	public static void viewData() throws SQLException {
		if(!rs.next()){	// 찾아온 데이터가 없을 때.
			System.out.println("!rs.next()");
		}
		else{			// 찾아온 데이터가 있을 때.
				System.out.println("rs.next()");
				txtPno.setText(String.valueOf(rs.getString("BNO")));	// grobal 변수로 선언해줘야 모든 메소드에서 사용 가능.
	
				txtPName.setText(rs.getString("PRODUCT"));
					
				txtCount.setText(rs.getString("BCOUNT"));
				
				txtDate.setText(rs.getString("BDATE"));
				
				txtPrice.setText(rs.getString("BPRICE"));
		}
	}
}
