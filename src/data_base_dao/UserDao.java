package data_base_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	//工具类
	public Connection getConnection() {
		Connection conn=null;
		try {		                              
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentlogin?characterEncoding=UTF-8", "root", "123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//登录
	public boolean loginUser(String name, String password,String sqlyuj) {
		conn = getConnection();
		String sql=sqlyuj;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//注册
	public boolean register(String jtf_value, String jpf_value) {
		conn=getConnection();
		String sql="insert into student_student values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, jtf_value);
			ps.setString(3, jpf_value);
			int i = ps.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	//保证用户名唯一
	public boolean RepeatName(String jtf_value) {
		conn=getConnection();
		String sql="select * from student_user where student_name=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, jtf_value);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// 查询数据库
	public ResultSet getRs(String sql){
		conn=getConnection();
		
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	//更新数据库
	public int getUpdate(String sql) {
		conn=getConnection();
		int i=0;
		try {
			ps=conn.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		}
		return i;
	}

}
