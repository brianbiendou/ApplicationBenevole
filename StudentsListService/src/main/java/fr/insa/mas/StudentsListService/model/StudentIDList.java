package fr.insa.mas.StudentsListService.model;

import java.util.ArrayList;
import java.util.List;

public class StudentIDList {
	List <Integer> studentlist;

	public StudentIDList() {
		this.studentlist = new ArrayList<Integer>();
	}

	public List<Integer> getStudentlist() {
		return studentlist;
	}

	public void saddStudentToList(Integer studentId) {
		this.studentlist.add(studentId);
	}
	
	
	

}
