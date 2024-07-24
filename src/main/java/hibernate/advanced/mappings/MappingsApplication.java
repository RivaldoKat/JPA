package hibernate.advanced.mappings;

import hibernate.advanced.mappings.dao.AppDAO;
import hibernate.advanced.mappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

//			createCourseAndStudents(appDAO);

//			findCourseAndStudents(appDAO);

//			findStudentAndCourses(appDAO);

//			addMoreCoursesForStudent(appDAO);

//			deleteCourse(appDAO);

			deleteStudent(appDAO);

		};

	}

	private void deleteStudent(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting student id: "  + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("Done");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;
		Student theStudent = appDAO.findCourseAndStudentsByStudentId(theId);

		// create more courses
		Course theCourse = new Course("JPA/Hibernate");
		Course theCourse1 = new Course("Springboot");

		// add courses to student
		theStudent.addCourse(theCourse);
		theStudent.addCourse(theCourse1);

		System.out.println("Updating the student: " + theStudent);
		System.out.println("associated courses: " + theStudent.getCourses());

		appDAO.update(theStudent);
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;
		Student theStudent = appDAO.findCourseAndStudentsByStudentId(theId);

		System.out.println("Loaded student: " + theStudent);
		System.out.println("Courses enrolled in: " + theStudent.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;
		Course theCourse = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course:" + theCourse);
		System.out.println("The student enrolled: " + theCourse.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course course = new Course("Pacman - How to score one million points");

		// create the student
		Student student = new Student("Thabang", "Sekoem", "thabangS@gamil.com");
		Student student1 = new Student("Katlego", "Sekome", "katS@gmail.com");

		// add students to the course
		course.addStudent(student);
		course.addStudent(student1);

		// save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("Associated students: " + course.getStudents());

		appDAO.save(course);

		System.out.println("Done");

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;
		System.out.println("Deleting the course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		int theId = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);

		System.out.println(course);

		System.out.println("Course reviews: " + course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course course = new Course("Hibernate");

		// add some reviews
		course.addReview(new Review("Feels good to finally do jpa!"));
		course.addReview(new Review("Wow! so much discovery"));
		course.addReview(new Review("Perfect in every way"));

		// save the course and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(course.getReviews());

		appDAO.save(course);
		System.out.println("Done!!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting the course id: " + theId);

		appDAO.deleteCourseById(theId);
	}

	private void updateCourse(AppDAO appDAO) {

        int theId = 10;

        // find the course
        System.out.println("Finding course ID: " + theId);
        Course course = appDAO.findCourseById(theId);
        System.out.println("The course name: " + course.getTitle());
        System.out.print("Update the course");
        course.setTitle("SCSC010");
        appDAO.update(course);
    }

    private void updateInstructor(AppDAO appDAO) {

		int theId = 21;

		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Updating the last name");
		instructor.setLastName("firstUpdate");
		appDAO.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 21;
		// finding the instructor
		System.out.println("Finding the instructor by ID: " + theId);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + instructor);

		System.out.println("Associated courses for instructor:" + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId = 8;
		// finding the instructor
		System.out.println("Finding the instructor by ID: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);

		// find course for instructor
		System.out.println("Finding courses for instructor id:" + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		instructor.setCourses(courses);
		System.out.println("The associated courses:" + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding the instructor by ID: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor temp = new Instructor("Rivaldo", "Sekome", "riva@gmail.com");
		InstructorDetail tempIntr = new InstructorDetail("Youtube.com/Riva", "Video Games");

		temp.setInstructorDetail(tempIntr);

		System.out.println("Saving instructor:" + temp);
		appDAO.save(temp);
		System.out.println("Done!");

		Course course = new Course("Cosc");
		Course course1 = new Course("SMTH");

		temp.add(course);
		temp.add(course1);

		System.out.println("The course: " + temp.getCourses());
		appDAO.save(temp);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting the user of ID: " +  theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!!");
	}

	private void findInstructorDetailById(AppDAO appDAO) {

		int id = 3;
		System.out.println("Instructor with ID:" + id);
		System.out.println(appDAO.findInstructorDetailById(id));
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Deleted");
	}

	private void findInstructorById(AppDAO appDAO) {

		int theId = 3;
		System.out.println("User of the ID:" + theId);
		System.out.println(appDAO.findInstructorById(theId));
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor temp = new Instructor("Rivaldo", "Sekome", "riva@gmail.com");
		InstructorDetail tempIntr = new InstructorDetail("Youtube.com/Riva", "Coding");

		temp.setInstructorDetail(tempIntr);

		System.out.println("Saving instructor:" + temp);
		appDAO.save(temp);
		System.out.println("Done!");
	}
}
