package example.dedo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import example.dedo.dao.PatientDao;
// import example.dedo.model.Patient;

@SpringBootApplication
public class DedoApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(DedoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}


}
