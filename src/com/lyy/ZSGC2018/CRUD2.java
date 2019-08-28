package com.lyy.ZSGC2018;

public class CRUD2 {
	private int id ; 	// Id
	private String name ;	//姓名
	private int age  ;	// 年龄
	private String addr ;	//地址
	private String sex ;	//性别
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "学生信息 [ID=" + id + ", 名字=" + name + ", 年龄=" + age + ", 地址=" + addr + ", 性别=" + sex + "]";
	}
	
	
}
