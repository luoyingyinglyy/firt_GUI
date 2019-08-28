package com.lyy.jdbcEmpCrud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * < 部门方法类 >
 * 
 * 通过SYSTEM.IN 输入 员工表，添加表 一个部门对应多个员工 1，查询所有部门，2查询输入部门，3，添加部门，4，删除部门
 * 5，添加员工，6，删除员工，7，修改员工，8查询所有员工，9查询输入员工，10，批量添加工员
 * 查询都是模糊查询，（查询部门时，需要显示部门下面所有的员工信息） 添加时，修改时，不能存在重复值 添加员工时，部门需要存在才能进行添加
 * 删除部门时，部门下面的所有员工也需要进行删除 删除员工时，如果发现该员工是部门里面的最后一个员工，需要把该部门删除
 * 
 * @author :lyy
 * @date :下午2:50:45 2019年3月23日
 *
 */
public class BuMenDao {

	static BuMenDao ma = new BuMenDao();
	static EmpDao empDao = new EmpDao();

	public static void main(String[] args) throws Exception {
		// ma.selectBuMen();//查询所有部门
		// ma.seleBuMen("人事部");//查询输入部门
		// ma.insert();//添加数据
		String a = "";
		System.out.println(a);
	}

	/**
	 * 连接数据库
	 * 
	 * @return
	 */
	public Connection getConn() {

		Connection conn = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 链接数据库 方法一
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/onemysql?&useSSL=false&serverTimezone=UTC", "root", "root");
			// 链接数据库，解决乱码 方法二
			// conn = (Connection)
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/onemysql?useUnicode=true&characterEncoding=UTF-8&user=root&password=root");
			// System.err.println("数据库链接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 查询所有部门
	 */
	public void selectBuMen() throws Exception {
		Connection conn = getConn();// 连接数据库
		String sql = "select * from bumen;";// 查询部门表的SQL语句
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.executeQuery();// 执行查询
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "   " + "name:" + rs.getString("name"));
		}
		conn.close();// 关闭连接
	}

	/**
	 * 查询指定部门
	 */
	public void seleBuMen(String aa) throws SQLException {
		Connection conn = getConn();
		String sql = "select * from bumen where name  like '%" + aa + "%'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.executeQuery();// 执行查询
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			System.out.println("部门编号: " + rs.getInt("id") + "   部门名称: " + rs.getString("name"));
			String bumenName = rs.getString("name");// 得到部门的名字
			Integer bumenID = ma.seleBuMenID(bumenName);// 得到部门的id
			empDao.seleYuanGongID(bumenID);
		}
		conn.close();// 关闭连接
	}

	/**
	 * 添加数据
	 * 
	 * @throws SQLException
	 */
	public void insert(BuMenEntity bumen) {

		Connection conn = getConn();
		String sql = "insert into bumen(name)values(?);";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, bumen.getName());
			BuMenDao bumendao = new BuMenDao();
			List<String> list = bumendao.getBuMenName();
			boolean aa = false;
			if (list == null || list.size() == 0) {
				ps.executeUpdate();
				System.out.println("数据添加成功");
			} else {
				for (String string : list) {//遍历集合 把集合中的数据一个一个的拿出来赋给string
					if (bumen.getName().equals(string)) { // bumen.getName也就是把对象的值拿出来跟list中的值做比较
						System.out.println("输入的值已存在");
						aa = true;
						break;
					}
				}
			}
			if(!aa) {
				ps.executeUpdate();
				System.out.println("数据添加成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 关闭连接
		}

	}

	/**
	 * 修改数据
	 * 
	 * @throws SQLException
	 */
	public void update(BuMenEntity bumen){
		Connection conn = getConn();
		String sql = "update bumen set name = ? where id = ?;";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, bumen.getName());// 要修改的部门名字
			ps.setInt(2, bumen.getId());// 要修改的部门ID
			ps.executeUpdate();// 执行修改
			System.out.println("修改成功");
			conn.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除数据
	 */
	public void delete(int a) {
		try {
			Connection conn = getConn();
			String sql = "delete from bumen where id= ?;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, a);// 要删除的ID
			ps.executeUpdate();// 执行删除
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据部门的名字获取部门的ID
	 */
	public int seleBuMenID(String name) {
		Connection conn = getConn();
		int bumenId = 0;
		try {
			String sql = "select id from bumen where name = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				bumenId = rs.getInt("id");
			}
		} catch (Exception e) {
			System.out.println("------------");
		}
		return bumenId;
	}

	/**
	 * 
	 * 查询出所有部门名字
	 */
	public List<String> getBuMenName() {
		Connection conn = getConn();
		List<String> list = new ArrayList<>();
		String sql = "select name from bumen;";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeQuery();
			ResultSet res = ps.getResultSet();
			while (res.next()) {
				list.add(res.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
