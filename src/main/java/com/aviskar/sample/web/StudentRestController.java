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

/**
 * Rest controller for student resources.
 * 
 * @author Aviskar
 * @version 1.0
 * @since 2016-11-29
 *
 */
@RestController
@RequestMapping("/api/rest/student")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private DomainSequenceService domainSequenceService;

	/**
	 * Fetch the info of student, whose id is passed in path variable.
	 * 
	 * @param id
	 * @return Returns the info of student with the help of id, passed in path
	 *         variable. If student with the given id doesn't exist, returns
	 *         {@link HttpStatus#NOT_FOUND} status.
	 */
	@RequestMapping(path = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long id) {
		Student resultStudent = studentService.findOne(id);
		if (resultStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(resultStudent, HttpStatus.OK);
	}

	/**
	 * Returns the info of all student.
	 * 
	 * @return Returns the info of all student.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudent() {
		return new ResponseEntity<List<Student>>(studentService.findAll(), HttpStatus.OK);
	}

	/**
	 * Creates the new student's info with the provided request body. The id for
	 * the student is provided by the application and hence, the id is ignored.
	 * The registration no. must be non-empty and unique.
	 * 
	 * @param student
	 * @return Returns the info of created student. If registration no. is or
	 *         empty (ignoring preceding & following spaces), returns
	 *         {@link HttpStatus#NOT_ACCEPTABLE} status. If registration no. is
	 *         not unique, returns {@link HttpStatus#CONFLICT} status.
	 */
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

	/**
	 * Updates the info of the student with the provided request body. The
	 * student to be updated is identified with the student's id, which is
	 * provided in request body. The registration no. must be non-empty and
	 * unique.
	 * 
	 * @param student
	 * @return Returns the info of updated student if student exist with the
	 *         given id. If student with the given id doesn't exist, returns
	 *         {@link HttpStatus#NOT_FOUND} status. If registration no. is or
	 *         empty (ignoring preceding & following spaces), returns
	 *         {@link HttpStatus#NOT_ACCEPTABLE} status. If registration no. is
	 *         not unique, returns {@link HttpStatus#CONFLICT} status.
	 */
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

	/**
	 * Removes the info of student, whose id is passed in path variable.
	 * 
	 * @param id
	 * @return Returns the info of deleted student. If student with the given id
	 *         doesn't exist, returns {@link HttpStatus#NOT_FOUND} status.
	 */
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