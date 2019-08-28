package com.lyy.ZSGC2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUD3 {
	 CRUD2 stus[] = new CRUD2[10];
	List<CRUD2> list = new ArrayList<CRUD2>();
	private String addInput(Scanner scann)
	{
		System.out.println("请输入学生ID");
		String id = scann.nextLine();
		return id ;
		
	}
	public void add (Scanner scann){
		String strId = addInput(scann);
		Integer id = Integer.valueOf(strId);
		for (int i = 0;i < stus.length; i++){
			CRUD2 stu = stus[i];
			if (stu != null)
			{
				if(stu.getId() == id)
				{
					System.out.println("已存在相同ID，请重新输入");
					i = -1;
					id = Integer.valueOf(addInput(scann));
							
				}
			}
		}
		
		System.out.println("请输入学生姓名");
		String name = scann.nextLine();
		System.out.println("请输入学生地址");
		String addr = scann.nextLine();
		System.out.println("请输入学生年龄");
		String age = scann.nextLine();
		System.out.println("请输入学生性别");
		String sex = scann.nextLine();
		
		//新增一个学生对象
		CRUD2  stu = new CRUD2();
		stu.setId(Integer.valueOf(id));
		stu.setName(name);
		stu.setAge(Integer.valueOf(age));
		stu.setAddr(addr);
		
		for(int i = 0; i < stus.length; i++){ // 新增的学生对象放入我们学生数组中
			 CRUD2 stul = stus[i];	//取出数组中对象下标的学生对象
			 if (stul == null) {	//用来判断是否为null
				 stus[i] = stu;
			break;	 //结束当前循环，不然后面所有的数组下标都会添加同一个学生对象
			 }
		}	
	}
	public void query(){	//判断是否有学生
		if (stus[0] == null) {
			System.out.println("当前没有学生信息！");
		} else {	//循环学生数组
			for (int i = 0; i < stus.length; i++){
				CRUD2 stu = stus[i];	//取出对应下标的对象
				if (stu != null){
					System.out.println(stu.toString());	//toString打印所有自己需要的值
				} else {
					break;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
