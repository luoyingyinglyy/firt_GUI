package com.lyy.jdbcEmpCrud;

/**
 * 部门实体类
 * @author :lyy
 * @date :下午4:38:19 2019年3月23日
 *
 */
public class BuMenEntity {
	
	private int id ;
	private String name ;
	
	public BuMenEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuMenEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
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
	@Override
	public String toString() {
		return "BuMenEntity [id=" + id + ", name=" + name + "]";
	}
}
