package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			
			// createInstructor(appDAO);
			
			// findInstructor(appDAO);
			
			// deleteInstructor(appDAO);
			
			// findInstructorDetail(appDAO);

			// deleteInstructorDetail(appDAO);

			// createInstructorWithCourses(appDAO);

			// findInstructorWithCourses(appDAO);

			// findCoursesForInstructor(appDAO);

			// findInstructorWithCoursesJoinFetch(appDAO);

			// updateInstructor(appDAO);

			//  updateCourse(appDAO);

			deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int id =  2;

		System.out.println("Deleting course id: "+id);
		appDAO.deleteCourseById(id);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 2;

		// finding the course
		System.out.println("Finding course by id: "+id);
		Course temp = appDAO.findCourseById(id);

		System.out.println("Updating the course id: "+id);
		temp.setTitle("OOPs");

		appDAO.updateCourse(temp);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 2;

		//find the instructor
		System.out.println("Finding the instructor id: "+id);
		Instructor temp = appDAO.findInstructorById(id);

		//update the instructor
		System.out.println("Updating instructor id: "+id);
		temp.setLastName("Shukla");

		appDAO.updateInstructor(temp);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		// join fetch is used to avoid the explicit
		// declaration for finding the courses associated
		// with the instructor

		int id = 1;
		System.out.println("Finding instructor id: "+id);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("temp Instructor: "+tempInstructor);
		System.out.println("Associated Courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 2;

		System.out.println("Finding instructor id: "+id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor: "+tempInstructor);

		//find courses for the instructor
		System.out.println("Finding courses for the instructor id: "+id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		tempInstructor.setCourses(courses);

		System.out.println("Associated courses: "+tempInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id: "+id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("Associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor = new Instructor(
				"Jatin",
				"Sharma",
				"jatin@outlook.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.jatinworks.com/youtube",
				"Musician");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses

		Course tempCourse1 = new Course("Piano");
		Course tempCourse2 = new Course("Guitar");
		Course tempCourse3 = new Course("Drums");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		// NOTE: this also saves the courses
		// because of CascadeType.PERSIST
		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 7;
		System.out.println("Deleting Instructor detail id: "+id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		int id = 5;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("tempInstructorDetail: "+tempInstructorDetail);

		System.out.println("Associated Instructor: "+tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int id = 1;
		System.out.println("Deleting Instructor: "+id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor id: "+id);
		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: "+tempInstructor);
		System.out.println("The associated Instructor details: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
//		Instructor tempInstructor = new Instructor(
//				"Chad",
//				"Darby",
//				"darby@gmail.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail(
//				"http://www.luv2code.com/youtube",
//				"Luv2Code");

		Instructor tempInstructor = new Instructor(
				"Saurabh",
				"Mehta",
				"saurabh@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.luv2code.com/youtube",
				"Piano");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: "+tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}
