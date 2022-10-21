package backend.harjoitustyo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends CrudRepository<Image, Long> {

	Optional<Image> findById(Long imageId);
	
	Image findImageByImageId(Long imageId);
	
	@Query(
	       value = "SELECT COUNT(*) as likes FROM image_likes WHERE image_id = :imageId",
	       nativeQuery = true)
	Integer findLikeCount(@Param("imageId") Long imageId);

}
