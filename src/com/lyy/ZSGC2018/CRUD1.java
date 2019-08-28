package com.lyy.ZSGC2018;

import java.util.Scanner;

public class CRUD1 {
		public static void main (String []args){
		Scanner scanner = new Scanner (System.in);
		CRUD33 CRUD3 = new CRUD33();
		
		while(true)
		{
			System.out.println("1.添加学生 2.删除学生 3.修改学生 4.查询学生 5.退出");
			String number = scanner.nextLine();
			//添加
			if ("1".equals(number))
			{
				CRUD3.add(scanner);
			}
			//删除
			else if("2".equals(number)){
				CRUD3.swlete(scanner);
			}
			//修改
			else if("3".equals(number)){
				CRUD3.upelate(scanner);
			}
			//查询
			else if("4".equals(number)){
				CRUD3.query(scanner);
			}
			//退出
			else if("5".equals(number)){
				System.out.println("感谢您老的输入");
			}
			//输入错误
			else{
				System.out.println("输入错误，请重新输入");
			}
		}
	}
}
