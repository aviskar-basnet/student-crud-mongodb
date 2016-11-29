package com.aviskar.sample.service;

import java.util.List;

import com.aviskar.sample.domain.Student;

/**
 * Service for {@link Student} domain.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link Student}
 *
 */
public interface StudentService {

	Student save(Student student);

	Student findOne(Long id);

	Student findByRegistrationNumber(String registrationNumber);

	void delete(Long id);

	List<Student> findAll();
}
