package backend.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.harjoitustyo.domain.AppUser;
import backend.harjoitustyo.domain.AppUserRepository;
import backend.harjoitustyo.domain.Category;
import backend.harjoitustyo.domain.CategoryRepository;
import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class RepositoryTests {
    
    @Autowired
    private ImageRepository imgRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private AppUserRepository userRepository;
    
    @Test
    @Order(1)
    public void testSignup() {
        AppUser testUser = new AppUser("testuser666", "test@gmail.com", "USER", "$2a$10$UWQ98kkAZXdgZc7BL4jfZ.K8hfU7SDxbPp9QqZ1.kDMghd2hcLqU2");
        userRepository.save(testUser);
        assertThat(testUser.getUserId()).isNotNull();
    }
    
    @Test
    @Order(2)
    public void testFindByUsername() {
        AppUser testUser = userRepository.findByUsername("user");
        assertEquals(testUser.getUsername(), "user");
    }
    
    @Test
    @Order(3)
    public void testCreateCategory() {
        Category testCategory = new Category("JUnit");
        categoryRepository.save(testCategory);
        assertThat(testCategory.getCategoryId()).isNotNull();
    }
    
    @Test
    @Order(4)
    public void testFindByCategoryName() {
        Category testCategory = categoryRepository.findCategoryByCategoryName("Test 1");
        assertEquals(testCategory.getCategoryName(), "Test 1");
    }
    
    @Test
    @Order(5)
    public void testUpload() {
        AppUser testUser = userRepository.findByUsername("user");
        Category testCategory = categoryRepository.findCategoryByCategoryName("Test 1");
        Image testImage = new Image("testfilename.jpg", "image/jpeg", "imagetitle", "description", testUser, testCategory);
        imgRepository.save(testImage);
        assertThat(testImage.getImageId()).isNotNull(); 
    }

}
