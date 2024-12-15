package fr.insa.mas.StudentsListService.model;

public class Evaluation {
	private int idStudent;
	private float avreage;
	
	public Evaluation(int idStudent, float avreage) {
		super();
		this.idStudent = idStudent;
		this.avreage = avreage;
	}
	
	public Evaluation() {
		
	}
	
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public float getAvreage() {
		return avreage;
	}
	public void setAvreage(float avreage) {
		this.avreage = avreage;
	}
	
	

}
