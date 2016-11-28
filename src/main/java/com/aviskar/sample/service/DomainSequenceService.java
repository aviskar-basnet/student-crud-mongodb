package com.aviskar.sample.service;

public interface DomainSequenceService {

	String STUDENT_SEQUENCE_ID = "Student";

	Long getNextSequenceValueById(String id);
}
