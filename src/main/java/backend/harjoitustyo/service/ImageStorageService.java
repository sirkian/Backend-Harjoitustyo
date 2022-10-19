package backend.harjoitustyo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;

@Service
public class ImageStorageService {

	@Autowired
	private ImageRepository imgRepository;
	
	LocalDateTime fileDate = LocalDateTime.now();
	
	//GENERATES AN UNIQUE FILENAME TO MAKE THINGS A LITTLE PRETTIER
	public String getFileName(Image image) {
		double ID = Math.random() + fileDate.getDayOfMonth();
		String fileID = Double.toString(ID).replace(".", "_").substring(0, 7);
		String fileName = ("imgstr_" + image.getAppUser().getUsername() + "_" + fileID);
		return fileName;
	}
		
	public Image saveFile(Image image, MultipartFile file) {			
		try {
			image.setFileName(getFileName(image));
			image.setFileType(file.getContentType());
			image.setData(file.getBytes());
			image.setImageDate(fileDate);
			return imgRepository.save(image);
		} catch(Exception e) {
			System.out.println("Failure @ ImageStorageService/saveFile");
			e.printStackTrace();
		}		
		return null;
	}
	
	public ResponseEntity<ByteArrayResource> getImageData(Long imageId) {
		Image image = this.getImage(imageId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + image.getFileName() + "\"")
				.body(new ByteArrayResource(image.getData()));		
	}
	
	public Image saveEditedFile(Image image) {
		ResponseEntity<ByteArrayResource> imagedata = this.getImageData(image.getImageId());	
		try {
			image.setFileName(getFileName(image));
			image.setFileType(imagedata.getHeaders().getContentType().toString());
			image.setData(imagedata.getBody().getByteArray());
			image.setImageDate(fileDate);
			return imgRepository.save(image);
		} catch(Exception e) {
			System.out.println("Failure @ ImageStorageService/saveEditedFile");
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
