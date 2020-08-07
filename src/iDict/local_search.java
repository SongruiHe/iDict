package iDict;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class local_search {
	Connection conn = dbconnect.getConnection();// 连接
	PreparedStatement pst = null;
	ResultSet rs = null;

	// 查询
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
					+"例句:\n" + rs.getString("lx");
		}
		
		return str;
	}

	// 查询 收藏夹专用
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

	// 插入
	public void insert(String word, String meaning) {
		int col = 0;
		String sql = "insert into words (Word,meaning) values (?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			pst.setString(2, meaning);

			col = pst.executeUpdate();
			if (col == 1)
				JOptionPane.showMessageDialog(null, "添加单词成功！");
			else
				JOptionPane.showMessageDialog(null, "添加单词失败！");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 删除
	public void delete(String word) {
		int col = 0;
		String sql = "delete from words where Word = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, word);
			col = pst.executeUpdate();
			if (col != 0)
				JOptionPane.showMessageDialog(null, "删除单词成功！");
			else
				JOptionPane.showMessageDialog(null, "删除单词失败！");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 统计词数
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
