package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.LabelUI;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        //get the courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all the courses with instructor
        for (Course tempCourse: courses) {
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        InstructorDetail temp = entityManager.find(InstructorDetail.class, id);

        // remove the associated object reference
        // break the bi-directional link
        temp.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(temp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", id);
        // execute query
        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        //create the query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course tempCourse = entityManager.find(Course.class, id);

        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                    "select c from Course c " +
                        "JOIN FETCH c.reviews " +
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);

        //execute the query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        // create a query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.students " +
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);

        //execute the query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {

        //create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "JOIN FETCH s.courses " +
                        "where s.id = :data", Student.class);
        query.setParameter("data", id);
        //execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {

        //retrieve the student
        Student temp = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(temp);
    }
}
