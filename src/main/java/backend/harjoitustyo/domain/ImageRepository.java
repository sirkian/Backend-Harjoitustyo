package backend.harjoitustyo.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

	Optional<Image> findById(Long imageId);
	
	Image findImageByImageId(Long imageId);
	
}
