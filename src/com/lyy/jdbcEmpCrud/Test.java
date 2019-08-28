package com.lyy.jdbcEmpCrud;


import java.util.Scanner;

/**
 * < 调用方法类 >
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
 * @date :下午7:31:01 2019年3月25日
 * 
 */
public class Test {
	static BuMenDao buMenDao = new BuMenDao();
	static EmpDao empDao = new EmpDao();
	static EmpEntity empEntity = new EmpEntity();
	static BuMenEntity buMenEntity1 = new BuMenEntity();

	public static void main(String[] args) {
		try{
			while(true) {
				Scanner sca = new Scanner(System.in);
				System.out.println("输入1部门管理,2员工管理");
				String admini = sca.nextLine();
				if("1".equals(admini)) {//输入1进入这里进行部门操作
					while(true) {
						System.out.println("0.退出 1.添加  2.查询指定部门   3.查询所有部门    4.删除");
						String bumen = sca.nextLine();
						if("1".equals(bumen)) {//在控制台输入1就进入这里做添加
							System.out.println("输入要添加的部门名字");
							String name = sca.nextLine(); //把在控制台输入的值赋给变量name
							buMenEntity1.setName(name);//要添加的字段
							buMenDao.insert(buMenEntity1);//调用BuMenDao类的添加方法
						}else if("2".equals(bumen)){//控制台输入2就进入这里做指定查询
							System.out.println("输入指定查询的部门名字");
							String name = sca.nextLine();
							buMenDao.seleBuMen(name);
						}else if("3".equals(bumen)) {//控制台输入3就进入这里查询全部部门数据
							System.out.println("查询所有部门数据中请稍等>>>>>");
							buMenDao.selectBuMen();//调用BuMenDao类查询全部数据的方法
						}else if("4".equals(bumen)) {//在控制台输入4就进入这里做删除功能
							buMenDao.selectBuMen();//先到BuMenDao类调用查询全部数据方法以方便下面做删除
							System.out.println("请参考以上数据输入要删除的部门ID");
							Integer id = sca.nextInt();
							buMenDao.delete(id);
						}else if("0".equals(bumen)){
							break;
						}	
					}
				}else if("2".equals(admini)) {//输入2到这里进行员工操作
					while(true) {
						System.out.println("1.添加  2.查询指定员工  3.查询所有员工  4.修改  5.删除");
						String emp = sca.nextLine();
						if("1".equals(emp)) {
							System.out.println("输入名字");
							String name = sca.nextLine();
							empEntity.setName(name);//要添加的字段
							
							System.out.println("输入性别");
							String tt = sca.nextLine();
							empEntity.setSex(tt);

							System.out.println("输入年龄");
							Integer age = sca.nextInt();
							empEntity.setAge(age);
							
							System.out.println("输入部门");
							Integer bumen = sca.nextInt();
							empEntity.setBumen(bumen);
							empDao.insertEmp(empEntity);
						}else if("2".equals(emp)) {//查询指定员工
							System.out.println("输入要查询的员工名字");
							String name = sca.nextLine();
							empDao.seleEmp(name);
						}else if ("3".equals(emp)) {//查询员工所有数据
							System.out.println("查询员工所有数据中>>>>>");
							empDao.selectEmp();
						}else if("4".equals(emp)){//修改
							empDao.selectEmp();//先查询出员工所有数据方便下面修改
							System.out.println("输入要修改的员工名字");
							String name = sca.nextLine();
							empEntity.setName(name);
							System.out.println("输入要修改的员工性别");
							String sex = sca.nextLine();
							empEntity.setSex(sex);
							System.out.println("输入要修改的员工年龄");
							Integer age = sca.nextInt();
							empEntity.setAge(age);
							System.out.println("输入你要修改的部门");
							Integer bumen = sca.nextInt();
							empEntity.setBumen(bumen);
							System.out.println("输入要修改的员工id");
							Integer id = sca.nextInt();
							empEntity.setId(id);
							empDao.updateEmp(empEntity);
						}else   if("5".equals(emp)){
							empDao.selectEmp();
							System.out.println("输入要删除的ID");
							Integer  id = sca.nextInt();
							empDao.deleteEmp(id);
						}else   if("0".equals(emp)){
							break;
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
