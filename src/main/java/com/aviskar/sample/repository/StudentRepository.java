package com.aviskar.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aviskar.sample.domain.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

	Student findByRegistrationNumber(String registrationNumber);
}
