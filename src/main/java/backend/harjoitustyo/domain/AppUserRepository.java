package backend.harjoitustyo.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

	AppUser findByUsername(String username);
	
	AppUser findByEmail(String email);
	
	@Query(
	        value = "SELECT * FROM app_user WHERE user_id = ( SELECT user_id FROM image WHERE image_id = :imageId )",
	        nativeQuery = true)
	AppUser findByImageId(@Param("imageId") Long imageId);
	
}
