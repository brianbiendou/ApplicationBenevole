package fr.insa.mas.studentManagementMS;



public class Student {
	private int id ;
	private String lastname;
	private String firtsName;
	public Student(int id, String lastname, String firtsName) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firtsName = firtsName;
	}
	
	public Student() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirtsName() {
		return firtsName;
	}
	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
	} 
	

}
