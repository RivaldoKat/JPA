package hibernate.advanced.mappings;

import hibernate.advanced.mappings.dao.AppDAO;
import hibernate.advanced.mappings.entity.Instructor;
import hibernate.advanced.mappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
//			createInstructor(appDAO);
//			findInstructorById(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Deleted");
	}

	private void findInstructorById(AppDAO appDAO) {

		int theId = 1;
		System.out.println("User of the ID:" + theId);
		System.out.println(appDAO.findInstructorById(theId));
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor temp = new Instructor("Katlego", "Sekome", "riv@gmail.com");
		InstructorDetail tempIntr = new InstructorDetail("Youtube.com/Riv", "Coding");

		temp.setInstructorDetail(tempIntr);

		System.out.println("Saving instructor:" + temp);
		appDAO.save(temp);
		System.out.println("Done!");
	}
}
