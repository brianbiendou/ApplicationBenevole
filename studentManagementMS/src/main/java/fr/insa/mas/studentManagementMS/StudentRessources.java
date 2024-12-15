package fr.insa.mas.studentManagementMS;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentRessources {
	
	@GetMapping("/students")
	public int StudentNumber() {
		return 200;
	}
	
	@GetMapping(value = "/students/{id}")
	public Student infoStudent(@PathVariable int id) {
		Student student = new Student(id, "Rosa", "Park");
		return student;
	}
	
	@PutMapping("/student/{id}/{lastName}/{firstName}")
	public void AddEtudiant(@PathVariable int id, @PathVariable String lastName, @PathVariable String firstName ) {
		System.out.println("Mise à jour réussie");
	}

}
