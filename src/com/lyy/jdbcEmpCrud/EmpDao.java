package com.lyy.jdbcEmpCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * < 员工方法类 >
 * 
 * 通过SYSTEM.IN 输入
 * 员工表，添加表    一个部门对应多个员工
 * 1，查询所有部门，2查询输入部门，3，添加部门，4，删除部门  5，添加员工，6，删除员工，7，修改员工，8查询所有员工，9查询输入员工，10，批量添加工员
 *  查询都是模糊查询，（查询部门时，需要显示部门下面所有的员工信息）
 *  添加时，修改时，不能存在重复值
 *  添加员工时，部门需要存在才能进行添加
 *  删除部门时，部门下面的所有员工也需要进行删除
 *  删除员工时，如果发现该员工是部门里面的最后一个员工，需要把该部门删除
 * @author :lyy
 * @date :下午7:30:06 2019年3月25日
 *
 */
public class EmpDao {
	
	public static void main(String[] args) throws Exception {
		EmpEntity ent = new EmpEntity();
		EmpDao emp = new EmpDao();
//		emp.selectEmp();//查询员工所有数据
//		emp.seleEmp("张");//查询指定数据
		emp.deleteEmp(13);
		
		/*ent.setName("111");
		ent.setAge(20);
		ent.setSex("男");
		ent.setBumen(103); 
		ent.setId(3);
		emp.updateEmp(ent);//修改数据*/
		
		
		//给EmpEntity中的变量用set方法赋值  (insertEmp方法)
		/*ent.setName("ddd");
		ent.setAge(45);
		ent.setSex("女");
		ent.setBumen(103);
		emp.insertEmp(ent);//添加数据*/
	}
	
	
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConn() {
		
		Connection conn = null;
		try {
			//加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			//链接数据库 方法一
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/onemysql?&useSSL=false&serverTimezone=UTC","root","root");
			// 链接数据库，解决乱码 方法二
			//conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/onemysql?useUnicode=true&characterEncoding=UTF-8&user=root&password=root");
			//System.err.println("数据库链接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * 查询所有员工
	 */
	public void selectEmp()throws Exception {
		Connection conn = getConn();//连接数据库
		String sql = "select * from yuangong;";//查询部门表的SQL语句
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.executeQuery();//执行查询
		ResultSet rs = ps.getResultSet();
		while(rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "  名字:"+rs.getString("name")+"  年龄:"+rs.getInt("age")+"  性别:"+rs.getString("sex")+"  部门:"+rs.getInt("bumen"));
		}
		conn.close();//关闭连接
	}
	
	
	
	/**
	 * 查询指定员工
	 */
	public void seleEmp(String aa) throws SQLException {
		Connection conn = getConn();
		String sql = "select * from yuangong where name  like ?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1,"%\\"+aa+"%");
		ps.executeQuery();//执行查询
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			System.out.println("id:" + rs.getInt("id") + "  名字:"+rs.getString("name")+"  年龄:"+rs.getInt("age")+"  性别:"+rs.getString("sex")+"  部门:"+rs.getInt("bumen"));
		}
		conn.close();//关闭连接
	}
	
	
	/**
	 * 添加数据
	 * @throws SQLException 
	 */
	public void insertEmp(EmpEntity emp) throws SQLException {
		
		Connection conn = getConn();
		String sql = "insert into yuangong(name,age,sex,bumen)values(?,?,?,?);";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1,emp.getName());
		ps.setInt(2,emp.getAge());
		ps.setString(3,emp.getSex());
		ps.setInt(4,emp.getBumen());
		ps.executeUpdate();
		System.out.println("数据添加成功");
		conn.close();//关闭连接
	}
	
	
	
	
	/**
	 * 修改数据
	 * @throws SQLException 
	 */
	public void updateEmp(EmpEntity emp){
		Connection conn = getConn();
		String sql = "update yuangong set name = ?,age = ?,sex = ?,bumen = ? where id = ?;";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1,emp.getName());//要修改的名字
			ps.setInt(2,emp.getAge());//要修改的年龄
			ps.setString(3,emp.getSex());//要修改的性别
			ps.setInt(4,emp.getBumen());//要修改的部门id 
			ps.setInt(5,emp.getId());//要修改的部门id 
			EmpDao empdao = new EmpDao();
			List<String> list = empdao.getEmpName();
			boolean boo = true;// 相当于设置一个 标识符，用来看修改后的员工名字是否重复
			if(list == null || list.size()== 0) {
				System.err.println("员工不存在！！！");
			}else {
				for (String string : list) {
					if(emp.getName().equals(string)) {
						System.out.println("员工名字已重复");
						boo = false;//如果修改后的员工名字重复就把boo的值改为false
					}
				}
			}
			if(boo) {
				ps.executeUpdate();//执行修改
				System.out.println("修改成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	 * 删除数据
	 */
	public void deleteEmp(int a) {
		try {
			Connection conn = getConn();
			String sql = "delete from yuangong where id= ?;";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,a);//要删除的ID
			ps.executeUpdate();//执行删除
			System.out.println("删除成功");
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据部门的ID查询出员工的信息
	 */
	public void seleYuanGongID(int bumen) {
		Connection conn = getConn();
		try {
			String sql = "select name from yuangong where bumen = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,bumen);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				System.out.println("该部门下有员工: " + rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("-----------------");
		}
	}
	
	/**
	 * 
	 * 查询出所有部门名字
	 */
	public List<String> getEmpName() {
		Connection conn = getConn();
		List<String> list = new ArrayList<>();
		String sql = "select name from yuangong;";
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
