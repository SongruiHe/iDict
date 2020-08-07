package iDict;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class local_search {
	Connection conn = dbconnect.getConnection();// ����
	PreparedStatement pst = null;
	ResultSet rs = null;

	// ��ѯ
	public String query(String word) throws SQLException {

		String sql = "select * from words where Word = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String str = null;
		while (rs.next()) {
			str = rs.getString("meaning") + "\n" + "[" + rs.getString("GQS") 
					+ " " + rs.getString("GQFC") + " "+ rs.getString("XZFC") 
					+ " " + rs.getString("FS") + "]" + "\n"
					+"����:\n" + rs.getString("lx");
		}
		
		return str;
	}

	// ��ѯ �ղؼ�ר��
	public String query_collect(String word) throws SQLException {
		String sql = "select meaning from words where Word = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			rs = pst.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String str = null;
		while (rs.next()) {
			str = rs.getString("meaning");
		}
		return str;
	}

	// ����
	public void insert(String word, String meaning) {
		int col = 0;
		String sql = "insert into words (Word,meaning) values (?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			pst.setString(2, meaning);

			col = pst.executeUpdate();
			if (col == 1)
				JOptionPane.showMessageDialog(null, "��ӵ��ʳɹ���");
			else
				JOptionPane.showMessageDialog(null, "��ӵ���ʧ�ܣ�");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// ɾ��
	public void delete(String word) {
		int col = 0;
		String sql = "delete from words where Word = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			col = pst.executeUpdate();
			if (col != 0)
				JOptionPane.showMessageDialog(null, "ɾ�����ʳɹ���");
			else
				JOptionPane.showMessageDialog(null, "ɾ������ʧ�ܣ�");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// ͳ�ƴ���
	public int count() {
		String sql = "select count(*) Word from words";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		try {
			if (rs.next()) {
				count = rs.getInt("Word");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
		
		return count;
	}

}
