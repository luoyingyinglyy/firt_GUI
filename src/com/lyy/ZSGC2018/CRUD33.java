package com.lyy.ZSGC2018;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CRUD33{
	Map<Integer,CRUD2>map = new HashMap<>();
	/**
	 * 添加学生
	 * @param a
	 */
	public void add(Scanner a){
		System.out.println("请输入学生ID");
		String id = a.nextLine();
		Set<Integer> aa = map.keySet();
		Iterator<Integer> ab = aa.iterator();
		boolean to = false;
		while (ab.hasNext()){
			if (ab.next()==Integer.valueOf(id)){
				System.out.println("以存在");
			to = true;
			break;
			}
		}
	if(!to){
		System.out.println("请输入学生姓名");
		String name = a.nextLine();
		System.out.println("请输入学生年龄");
		String age = a.nextLine();
		
		System.out.println("请输入学生ID");
		String Id = a.nextLine();
		
		System.out.println("请输入学生地址");
		String addr = a.nextLine();
		System.out.println("请输入学生性别");
		String sex = a.nextLine();
		CRUD2 stu = new CRUD2();
		stu.setAddr(addr);
		stu.setName(name);
		stu.setAge(Integer.valueOf(age));
		stu.setSex(sex);
		stu.setId(Integer.valueOf(id));
			map.put(stu.getId(),stu);
	}
	}
	public void swlete(Scanner a){
		System.out.println("请输入你要删除的ID");
		Integer c  = Integer.valueOf(a.nextLine());
		if(map.get(c) != null){
			map.remove(c);
			System.out.println("删除成功");
		}else{
			System.out.println("删无此人");
		}
	}
	/**
	 * 修改
	 * @param a
	 */
	public void upelate(Scanner a){
		System.out.println("请输入要修改学生的id");
		String s = a.nextLine();
		Integer ss = Integer.valueOf(s);
		System.out.println("请输入新学生年龄");
        String NewAge = a.nextLine();
		System.out.println("请输入新学生姓名");
		String NewName = a.nextLine();
		System.out.println("请输入新学生地址");
		String NewAddr= a.nextLine();
		System.out.println("请输入新学生性别");
		String NewSex = a.nextLine();
	    CRUD2 stu1 = new CRUD2();
		stu1.setAddr(NewAddr);
		stu1.setName(NewName);
		stu1.setAge(Integer.valueOf(NewAge));
		stu1.setSex(NewSex);
		stu1.setId(ss);
		if(map.get(ss) != null){
			map.put(stu1.getId(), stu1);
		}else{
			System.out.println("找不到要修改的学生");
			
		}
	}
	/**
	 * 查询
	 * @param a
	 */
	public  void query(Scanner d) {
		//System.out.println(a);
		
		if(map.isEmpty()){
			//集合不能用==null来判断里面是否为空；只能用isEmpty;来判断
			System.out.println("此集合为空");
		}else {
			System.out.println("请输入要查询学生的Id");
			String s = d.nextLine();
			Integer ss = Integer.valueOf(s);
			
			Set<Integer> aa = map.keySet();//这里是Entry是返回set里的键-值关系
			Iterator<Integer> ab = aa.iterator();
			//Iterator<Map.Entry<Integer, Student>> ab=a.entrySet().iterator();
			boolean ise = false;
			while( ab.hasNext())//这里是判断ab这个迭代器里还有没有下一个值
			{
				Integer a3 = ab.next();
				//Map.Entry<Integer, Student> entry=ab.next();
				//int a3=entry.getKey();
				if(ss==a3){
					System.out.println(map.get(ss));
					ise = true;
					break;
				}
			}
			if(!ise){
				System.out.println("查无此人");
			}
		}
	}
}
