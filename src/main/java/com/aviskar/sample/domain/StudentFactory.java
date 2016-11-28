package com.aviskar.sample.domain;

@Deprecated
public class StudentFactory implements BaseStudentFactory {

	private StudentFactory() {

	}

	private static final BaseStudentFactory STUDENT_FACTORY = new StudentFactory();

	public static BaseStudentFactory getInstance() {
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
