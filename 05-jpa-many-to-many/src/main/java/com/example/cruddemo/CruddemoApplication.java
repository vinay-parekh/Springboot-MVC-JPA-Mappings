package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
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
			//createCourseAndStudents(appDAO);

			//retrieveCourseAndStudents(appDAO);

			//retrieveStudentAndCourses(appDAO);

			//addMoreCoursesForStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 1;

		System.out.println("Deleting Student id: "+id);

		appDAO.deleteStudentById(id);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 2;

		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(id);

		//create more courses
		Course tempCourse1 = new Course("OOPs");
		Course tempCourse2 = new Course("Web Dev");

		//add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Update Student: "+tempStudent);
		System.out.println("Associated courses: "+tempStudent.getCourses());

		appDAO.updateStudent(tempStudent);

		System.out.println("Done!");
	}

	private void retrieveStudentAndCourses(AppDAO appDAO) {
		int id = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(id);

		System.out.println("Loaded student: "+tempStudent);
		System.out.println("Courses: "+tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void retrieveCourseAndStudents(AppDAO appDAO) {
		int id = 2;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Loaded course: "+tempCourse);
		System.out.println("Students: "+tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("DSA");

		// create the students
		Student tempStudent1 = new Student("Shivin",
				"Shetty",
				"shivin@gmail.com");
		Student tempStudent2 = new Student("Shanaya",
				"Singh",
				"shanaya@gmail.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course: "+tempCourse);
		System.out.println("Associated students: "+tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int id = 1;
		System.out.println("Deleting course id: "+id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course
		int id = 1;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(id);

		// print the course
		System.out.println("Course with id: "+id);
		System.out.println(tempCourse);

		// print the review
		System.out.println("Associated reviews with the course id: "+id);
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("Machine Learning");

		//add reviews
		tempCourse.addReview(new Review("Great course"));
		tempCourse.addReview(new Review("Very informative"));
		tempCourse.addReview(new Review("Well explained"));

		//save the course
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int id =  4;

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
