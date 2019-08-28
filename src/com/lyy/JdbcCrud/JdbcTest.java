package com.lyy.JdbcCrud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author :lyy
 * @date :下午2:58:09 2019年3月22日
 *
 */

public class JdbcTest {
	public static void main(String[] args) {
		JdbcTest jdbctest = new JdbcTest();
//		jdbctest.insert();//添加数据
//		jdbctest.update();//修改数据
//		jdbctest.delete();//删除数据
		jdbctest.select();//查询数据
		
	}
	
	/**
	 * 链接数据库   
	 * @return
	 */
	public Connection getCon() {
		Connection conn = null;
		try {
			//加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//链接数据库 方法一
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/onemysql?&useSSL=false&serverTimezone=UTC","root","root");
			// 链接数据库，解决乱码 方法二
			//conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/onemysql?useUnicode=true&characterEncoding=UTF-8&user=root&password=root");
			System.err.println("数据库链接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 关闭所有连接
	 * 
	 * @param conn
	 * @param ps
	 * @param st
	 * @param rs
	 */
	public void closeAll(Connection conn, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			// 当连接不等于空的时候关闭
			if (null != conn) {
				conn.close();
			}
			if (null != ps) {
				ps.close();
			}
			if (null != st) {
				st.close();
			}
			if (null != rs) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加数据
	 */
	public void insert() {
		try {
			Connection conn = getCon();
			String sql = "insert into aa(name,age,birthday)values(?,?,?);";
			// 通过数据库连接加载指定的SQL语句，预编译sql，一般会使用预编译sql，比较安全
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql); // 执行sql
			ps.setString(1,"金针菇");
			ps.setInt(2,20);
			ps.setDate(3, new Date(System.currentTimeMillis()));
			ps.executeUpdate();
			// Statement st = (Statement) conn.createStatement();
			// 非预编译sql不推荐使用
			// st.executeUpdate(sql);
			System.err.println("数据添加成功");
			closeAll(conn, ps, null, null);// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询数据
	 */
	public void select() {
		try {
			Connection conn = getCon();// 连接数据库
			String sql = "select * from aa";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeQuery();// 执行查询
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				System.err.println("id:" + rs.getInt("id") + "   " + "name:" + rs.getString("name"));
				// System.out.println("id:" + rs.getInt(1) + " " + "name:" +
				// rs.getString(2));
			}
			closeAll(conn, ps,null, rs);// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 删除数据
	 */
	public void delete() {
		try {
			Connection conn = getCon();
			String sql = "delete from aa where id=101;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();//执行删除
			System.err.println("删除成功");
			closeAll(conn,ps,null,null);//关闭连接
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改数据
	 */
	public void update() {
		try {
			Connection conn = getCon();
			String sql = "update aa set name = ? where id  =?;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1,"豆腐辣条");//设值
			ps.setInt(2,124);
			ps.executeUpdate();//执行修改
			System.err.println("修改成功");
			closeAll(conn, ps, null,null);//关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
