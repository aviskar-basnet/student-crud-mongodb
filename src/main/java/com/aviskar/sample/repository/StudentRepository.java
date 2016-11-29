package com.aviskar.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aviskar.sample.domain.Student;

/**
 * Repository for {@link Student} domain.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link Student}
 *
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

	Student findByRegistrationNumber(String registrationNumber);
}
