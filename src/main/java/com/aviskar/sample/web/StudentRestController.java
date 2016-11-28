package com.aviskar.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aviskar.sample.domain.Student;
import com.aviskar.sample.service.DomainSequenceService;
import com.aviskar.sample.service.StudentService;

@RestController
@RequestMapping("/api/rest/student")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DomainSequenceService domainSequenceService;

	@RequestMapping(path = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long id) {
		Student resultStudent = studentService.findOne(id);
		if (resultStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(resultStudent, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudent() {
		return new ResponseEntity<List<Student>>(studentService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		if (student.getRegistrationNumber() == null || student.getRegistrationNumber().trim().equals("")) {
			return new ResponseEntity<Student>(HttpStatus.NOT_ACCEPTABLE);
		}
		Student resultStudent = studentService.findByRegistrationNumber(student.getRegistrationNumber().trim());
		if (resultStudent != null) {
			return new ResponseEntity<Student>(HttpStatus.CONFLICT);
		}
		Long nextSequenceValueForNewStudent = domainSequenceService
				.getNextSequenceValueById(DomainSequenceService.STUDENT_SEQUENCE_ID);
		student.setId(nextSequenceValueForNewStudent);
		Student savedStudent = studentService.save(student);
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (student.getId() == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		if (student.getRegistrationNumber() == null || student.getRegistrationNumber().trim().equals("")) {
			return new ResponseEntity<Student>(HttpStatus.NOT_ACCEPTABLE);
		}
		Student resultStudent = studentService.findOne(student.getId());
		if (resultStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		resultStudent = studentService.findByRegistrationNumber(student.getRegistrationNumber().trim());
		if (resultStudent != null && !resultStudent.getId().equals(student.getId())) {
			return new ResponseEntity<Student>(HttpStatus.CONFLICT);
		}
		Student updatedStudent = studentService.save(student);
		return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
	}

	@RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> removeStudentById(@PathVariable("studentId") Long id) {
		Student resultStudent = studentService.findOne(id);
		if (resultStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		studentService.delete(id);
		return new ResponseEntity<Student>(resultStudent, HttpStatus.OK);
	}
}