package com.aviskar.sample.domain;

/**
 * Implementation of {@link StudentFactory}.
 * 
 * @author Aviskar
 * @since 1.0
 * @see {@link Student}
 * @deprecated It is not being used in this version.
 * 
 */
@Deprecated
public class StudentFactoryImpl implements StudentFactory {

	private StudentFactoryImpl() {

	}

	private static final StudentFactory STUDENT_FACTORY = new StudentFactoryImpl();

	public static StudentFactory getInstance() {
		return STUDENT_FACTORY;
	}

	@Override
	public Student createStudent(String type) {
		Student student = null;
		if (type.equals(MORNING_TYPE)) {
			student = new MorningStudent();
		} else if (type.equals(AFTERNOON_TYPE)) {
			student = new AfternoonStudent();
		} else if (type.equals(EVENING_TYPE)) {
			student = new EveningStudent();
		}
		return student;
	}
}
