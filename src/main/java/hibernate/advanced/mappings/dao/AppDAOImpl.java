package hibernate.advanced.mappings.dao;

import hibernate.advanced.mappings.entity.Course;
import hibernate.advanced.mappings.entity.Instructor;
import hibernate.advanced.mappings.entity.InstructorDetail;
import hibernate.advanced.mappings.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor){
        entityManager.merge(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        
        // retrieve the instructor 
        Instructor instructor = entityManager.find(Instructor.class, theId);
        
        // get the course
        List<Course> courses = instructor.getCourses();

        // break association of all courses for the instructor
        for(Course course: courses) {
            instructor.setCourses(null);
        }

        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail temp = entityManager.find(InstructorDetail.class, theId);
        temp.getInstructor().setInstructorDetail(null);
        entityManager.remove(temp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", theId);

        // execute the query
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                 + "JOIN FETCH i.courses "
                 + "where i.id = :data", Instructor.class
        );

        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);

    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        entityManager.remove(entityManager.find(Course.class, theId));
    }

    @Override
    @Transactional
    public void save(Course  course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
               + "JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class
        );
        query.setParameter("data", theId);

        // execute the query
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class
        );
        query.setParameter("data", theId);

        // execute the query
        return query.getSingleResult();
    }

    @Override
    public Student findCourseAndStudentsByStudentId(int theId) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class
        );
        query.setParameter("data", theId);

        // execute the query
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // retrieve the student
        Student theStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(theStudent);
    }
}
