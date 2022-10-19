package backend.harjoitustyo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;

@RestController
public class ImageControllerREST {
	
	
	@Autowired
	private ImageRepository imgRepository;
	
	
	@GetMapping("/rest/images")
	public Iterable<Image> getImages() {
		return imgRepository.findAll();
	}
	
	@PostMapping("/rest/images")
	public Image uploadImage(@RequestBody Image image) {
		return imgRepository.save(image);
	}
	
	@GetMapping("/rest/images/{imageId}")
	public Optional<Image> getImageById(@PathVariable("imageId") Long imageId) {
		return imgRepository.findById(imageId);
	}
	
	@PutMapping("/rest/images/{imageId}")
	public Image editImage(@RequestBody Image image, @PathVariable("imageId") Long imageId) {
		image.setImageId(imageId);
		return imgRepository.save(image);
	}
	
	@DeleteMapping("/rest/images/{imageId}")
	public Iterable<Image> deleteImage(@PathVariable("imageId") Long imageId) {
		imgRepository.deleteById(imageId);
		return imgRepository.findAll();
	}

}
