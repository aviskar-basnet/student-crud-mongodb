package com.aviskar.sample.service;

import com.aviskar.sample.domain.DomainSequence;

/**
 * Service for {@link DomainSequence} domain.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link DomainSequence}
 *
 */
public interface DomainSequenceService {

	String STUDENT_SEQUENCE_ID = "Student";

	Long getNextSequenceValueById(String id);
}
