package backend.harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.harjoitustyo.domain.AppUser;
import backend.harjoitustyo.domain.AppUserRepository;
import backend.harjoitustyo.domain.Category;
import backend.harjoitustyo.domain.CategoryRepository;
import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;

@SpringBootApplication
public class HarjoitustyoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}
	
	@Autowired
	AppUserRepository uRepo;
	
	@Autowired
	ImageRepository iRepo;
	
	@Autowired
	CategoryRepository cRepo;
	
	@Override
	public void run(String... args) throws Exception {
		cRepo.save(new Category("Test 1"));
		cRepo.save(new Category("Test 2"));
		cRepo.save(new Category("Test 3"));
		uRepo.save(new AppUser("admin", "admin@gmail.com", "ADMIN", "$2a$10$Xp67oEDHyODcnTzkIIp9z.SpmmpZg33mqZe/jvaSHMnpWtEQGov5e"));
		uRepo.save(new AppUser("user", "user@gmail.com", "USER", "$2a$10$Rc25Yhstdcr9Ce3WcQFKLeHT3nN1Yr.ud6M0AywXA8Q1tidWcdvqy"));
	}

}
