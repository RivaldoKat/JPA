package hibernate.advanced.mappings.dao;

import hibernate.advanced.mappings.entity.Instructor;

public interface AppDAO  {

    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

}
