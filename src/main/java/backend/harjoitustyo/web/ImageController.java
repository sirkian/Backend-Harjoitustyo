package backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;
import backend.harjoitustyo.service.ImageStorageService;

@Controller
public class ImageController {

	@Autowired
	private ImageStorageService imgService;
	
	@Autowired
	private ImageRepository imgRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("images", imgRepository.findAll());
		return "index";
	}
	
	@GetMapping("/images")
	public String getAll(Model model) {
		model.addAttribute("images", imgRepository.findAll());
		return "images";
	}
	
	@GetMapping("/images/{imageId}")
	public String getImage(@PathVariable("imageId") Long imageId, Model model) {
		model.addAttribute("image", imgRepository.findById(imageId));
		return "image";
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "upload";
	}
	
	@PostMapping("/upload/upload")
	public String uploadImage(@RequestParam("image") MultipartFile file) {
		imgService.saveFile(file);
		return "redirect:/";
	}
	
	@GetMapping("/download/{imageId}")
	public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable Long imageId) {
		Image image = imgService.getImage(imageId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + image.getFileName() + "\"")
				.body(new ByteArrayResource(image.getData()));		
	}
}
