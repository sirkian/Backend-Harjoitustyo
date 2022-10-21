package backend.harjoitustyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import backend.harjoitustyo.domain.AppUserRepository;
import backend.harjoitustyo.domain.CategoryRepository;
import backend.harjoitustyo.domain.ImageRepository;

@SpringBootTest
class HarjoitustyoApplicationTests {
    
    @Autowired
    private ImageRepository imgRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private AppUserRepository userRepository;

	@Test
	void contextLoads() throws Exception {
	    assertThat(imgRepository).isNotNull();
	    assertThat(categoryRepository).isNotNull();
	    assertThat(userRepository).isNotNull();
	}

}
