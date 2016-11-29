package com.aviskar.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Student's view controller.
 * 
 * @author Aviskar
 * @version 1.0
 * @since 2016-11-29
 *
 */
@Controller
public class StudentController {

	/**
	 * Returns the index page.
	 * 
	 * @return Returns the index page.
	 */
	@RequestMapping("/")
	public String getStudentIndexPage() {
		return "index";
	}
}
