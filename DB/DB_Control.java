package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class DB_Control {
	// *�̱��� �ڵ�--------------------------------------

	// ���ο��� ��ü ����
	private static DB_Control instance = new DB_Control();

	// ������ ��ü�� ��ȯ
	public static DB_Control GetInstance() {
		return instance;
	}
	//*--------------------------------------------------
	
	List<String> list = new ArrayList<String>();
	List<String> list_id = new ArrayList<String>();
	Connection conn = null;
	//DB����
	public Connection getConnection() throws SQLException {
		Connection conn = null;

		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/java_project",
				"root", "5340");

		return conn;
	}


	//�˻�
	public List<String> Select(String id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = String
					.format("SELECT trackingnumber, start, end FROM parcel where id = '%s';",id);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
			}
			
			
			sql = String.format("SELECT name, phone, addr FROM signup where id = '%s';", id);
			ResultSet rs_2 = stmt.executeQuery(sql);
			
			while (rs_2.next()) {
				list.add(rs_2.getString(1));
				list.add(rs_2.getString(2));
				list.add(rs_2.getString(3));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
		return null;
	}

	//�����Է�
	public void Insert(String TN, String ID, String DS, String DE) {
		 
		try {
			Connection conn = getConnection();
			String sql = "INSERT INTO parcel VALUES (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ������ binding
			pstmt.setString(1, TN);
			pstmt.setString(2, ID);
			pstmt.setString(3, DS);
			pstmt.setString(4, DE);

			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ �Է� ����");
				conn.close();
			} else {
				System.out.println("������ �Է� ����");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
	}

	//��������
	public void Delete(String ID) {
		try {

			Connection conn = getConnection();
			String sql = String.format("delete from sginup where id = '%s'", ID);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ ���� ����");
				conn.close();
			} else {
				System.out.println("������ ���� ����");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
	}

	//������ ����
	public void DS_Update(String ID, String DS) {
		 
		try {
			Connection conn = getConnection();
			String sql = String
					.format("Update parcel set start = '%s' where id = '%s'; ",
							DS, ID);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ ���� ����");
				conn.close();
			} else {
				System.out.println("������ ���� ����");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
	}

	//������ ����
	public void DE_Update(String ID, String DE) {
		try {
			Connection conn = getConnection();
			String sql = String
					.format("Update parcel set End = '%s' where ID = '%s'; ",
							DE, ID);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("������ ���� ����");
				conn.close();
			} else {
				System.out.println("������ ���� ����");
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
	}

	
	//ȸ������
	public String Mem_Insert(String id, String pw, String name, String phone, String addr) {
		try {
			Connection conn = getConnection();
			String sql = "INSERT INTO SIGNUP VALUES (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4. ������ binding
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, addr);
			
			int count = pstmt.executeUpdate();
			if (count != 0) {
				conn.close();
				return "����";
				
			}
		} catch (SQLException e) {
			return e.getMessage();
		}
		return null;
	}
	
	//�α���
	public String Login(String id, String pw) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = String.format("SELECT pw FROM signup WHERE id = '%s' AND pw ='%s'",id, pw);
			

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String str = rs.getString(1); 
			conn.close();
			return str;
			
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
		return null;
	}
	
//	//��۹�ȣ �˻�
//	public String Select_Tn(String id) {
//		try {
//			Connection conn = getConnection();
//			Statement stmt = conn.createStatement();
//			String sql = String.format("select trackingnumber from parcel where phone = (SELECT phone FROM signup where id = '%s')",id);
//					
//
//			ResultSet rs = stmt.executeQuery(sql);
//			rs.next();
//			
//			return rs.getString(1);
//			
//		} catch (SQLException e) {
//			System.out.println("���� " + e);
//		}
//		return null;
//	}

	//���̵� ���� �˻�
	public List<String> Select_AllID() {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			String sql = "SELECT id FROM signup;";
			

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				list_id.add(rs.getString("id"));
				
			}
			conn.close();
			return list_id;

			
		} catch (SQLException e) {
			System.out.println("���� " + e);
		}
		return null;
		
	}
	
	
	
	
	
}