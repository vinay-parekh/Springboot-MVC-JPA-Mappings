package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {

		int id = 1;
		System.out.println("Deleting Instructor: "+id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

//	private void findInstructor(AppDAO appDAO) {
//		int id = 2;
//		System.out.println("Finding instructor id: "+id);
//		Instructor tempInstructor = appDAO.findInstructorById(id);
//		System.out.println("Instructor: "+tempInstructor);
//		System.out.println("The associated Instructor details: "+tempInstructor.getInstructorDetail());
//	}

//	private void createInstructor(AppDAO appDAO) {
//
//		// create the instructor
////		Instructor tempInstructor = new Instructor(
////				"Chad",
////				"Darby",
////				"darby@gmail.com");
////
////		InstructorDetail tempInstructorDetail = new InstructorDetail(
////				"http://www.luv2code.com/youtube",
////				"Luv2Code");
//
//		Instructor tempInstructor = new Instructor(
//				"Krish",
//				"Soni",
//				"krish@gmail.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail(
//				"http://www.luv2code.com/youtube",
//				"Guitar");
//
//		tempInstructor.setInstructorDetail(tempInstructorDetail);
//
//		System.out.println("Saving instructor: "+tempInstructor);
//		appDAO.save(tempInstructor);
//
//		System.out.println("Done!");
//	}
}
