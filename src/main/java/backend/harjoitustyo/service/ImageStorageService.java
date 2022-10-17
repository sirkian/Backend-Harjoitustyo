package backend.harjoitustyo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;

@Service
public class ImageStorageService {

	@Autowired
	private ImageRepository imgRepository;
	
	public Image saveFile(MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		
		try {
			Image image = new Image(fileName, file.getContentType(), file.getBytes());
			return imgRepository.save(image);
		} catch(Exception e) {
			System.out.println("Failure in ImageStorageService/saveFile");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Optional<Image> getImage(Long imageId) {
		return imgRepository.findById(imageId);
	}
	
	public Iterable<Image> getImages() {
		return imgRepository.findAll();
	}
}
