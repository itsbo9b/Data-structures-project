package project;

import java.io.Serializable;

public class Student implements Serializable {
	
	private int id;
	private double GPA;
	private String address;
	private String name;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getGPA() {
		return GPA;
	}


	public void setGPA(double gPA) {
		GPA = gPA;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Student(int id, double gPA, String address, String name) {
		super();
		this.id = id;
		GPA = gPA;
		this.address = address;
		this.name = name;
	}


	public Student() {
		
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", GPA=" + GPA + ", address=" + address + ", name=" + name + "]";
	}
	
	

}
