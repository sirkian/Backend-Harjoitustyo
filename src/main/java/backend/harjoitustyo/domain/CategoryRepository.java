package backend.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findByCategoryName(String categoryName);
	
	Category findCategoryByCategoryName(String categoryName);
	
	Category findCategoryByCategoryId(Long categoryId);

}
