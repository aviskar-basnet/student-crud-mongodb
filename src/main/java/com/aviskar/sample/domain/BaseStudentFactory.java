package com.aviskar.sample.domain;

@Deprecated
public interface BaseStudentFactory {

	static final String MORNING_TYPE = "M";

	static final String AFTERNOON_TYPE = "A";

	static final String EVENING_TYPE = "E";

	Student createStudent(String type);
}
