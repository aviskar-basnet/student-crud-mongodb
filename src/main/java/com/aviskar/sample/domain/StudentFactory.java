package com.aviskar.sample.domain;

/**
 * Factory of {@link Student} for creating instance of it.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link Student}
 * @deprecated It is not being used in this version.
 * 
 */
@Deprecated
public interface StudentFactory {

	static final String MORNING_TYPE = "M";

	static final String AFTERNOON_TYPE = "A";

	static final String EVENING_TYPE = "E";

	Student createStudent(String type);
}
