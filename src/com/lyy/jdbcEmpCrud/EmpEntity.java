package com.lyy.jdbcEmpCrud;

/**
 * 员工实体类
 * @author :lyy
 * @date :下午2:54:54 2019年3月23日
 *
 */
public class EmpEntity {
	private int id ;
	private String name;
	private int age;
	private String sex;
	private int bumen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getBumen() {
		return bumen;
	}
	public void setBumen(int bumen) {
		this.bumen = bumen;
	}
	@Override
	public String toString() {
		return "EmpEntity [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", bumen=" + bumen + "]";
	}
	

}
