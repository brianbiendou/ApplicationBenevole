package fr.insa.mas.StudentsListService.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.StudentsListService.model.*;

@RestController
@RequestMapping("/students")
public class StudentListResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("ids/{idSpeciality}")
	public StudentIDList getIDStudents(@PathVariable("idSpeciality") String speciality) {
		
		//simulate
		StudentIDList studentsId = new StudentIDList();
		studentsId.saddStudentToList(0);
		studentsId.saddStudentToList(1);
		studentsId.saddStudentToList(2);
		studentsId.saddStudentToList(3);
		
		return studentsId;
	}
	
	@GetMapping("/all/{idSpeciality}")
	public List<Student> getStudents(@PathVariable("idSpeciality") String speciality){
		
		//Simulate the database using a list of integers
		StudentIDList students = new StudentIDList();
		students.saddStudentToList(0);
		students.saddStudentToList(1);
		students.saddStudentToList(2);
		students.saddStudentToList(3);
		
		//Instanciate RestTemplate for Rest colls
		int i=0;
		List<Student> listStudents = new ArrayList<Student>();
		
		while (i<students.getStudentlist().size()) {
			//Call the MS to get student's information.
			//The result is deserialized into Student Infos java object
			
			StudentInfos etudinfos = restTemplate.getForObject("http://StudentInfoService/student/"+i, StudentInfos.class);
			//Call the MS to get the student's evaluation.
			//The result is deserialized into Evaluation java object.
			Evaluation eval = restTemplate.getForObject("http://StudentEvalService/evaluation/" + i, Evaluation.class);
			//Instanciate a student with his id, his first name, last name, average and store it in the list
			listStudents.add(new Student(i,etudinfos.getFirstName(),etudinfos.getLastName(),eval.getAvreage()));
			i++;
		}
		return listStudents;
	}
	
	

}
