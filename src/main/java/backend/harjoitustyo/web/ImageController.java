package backend.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import backend.harjoitustyo.domain.AppUser;
import backend.harjoitustyo.domain.AppUserRepository;
import backend.harjoitustyo.domain.CategoryRepository;
import backend.harjoitustyo.domain.Image;
import backend.harjoitustyo.domain.ImageRepository;
import backend.harjoitustyo.service.ImageStorageService;

@Controller
public class ImageController {

	@Autowired
	private ImageStorageService imgService;
	
	@Autowired
	private ImageRepository imgRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AppUserRepository userRepository;
	
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
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("image", new Image());
		model.addAttribute("categories", categoryRepository.findAll());
		return "upload";
	}
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@PostMapping("/upload/upload")
	public String uploadImage(
			@Valid @ModelAttribute("image") Image image, 
			@RequestParam("image") MultipartFile file, 
			BindingResult bindingResult, Model model, 
			Authentication authentication) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "upload";
		} 
			AppUser user = userRepository.findByUsername(authentication.getName());
			image.setAppUser(user);
			imgService.saveFile(image, file);
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
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/image/{imageId}")
	public String editImage(@PathVariable("imageId") Long imageId, Model model) {
		model.addAttribute("image", imgRepository.findById(imageId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "edit-image";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/edit/image/save")
	public String saveEditedImage(
			@Valid Image image,
			BindingResult bindingResult, Model model,
			Authentication authentication) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "edit-image";
		}
		AppUser user = userRepository.findByUsername(authentication.getName());
		image.setAppUser(user);
		imgService.saveEditedFile(image);
		return "redirect:/";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/image/{imageId}")
	public String deleteImage(@PathVariable("imageId") Long imageId) {
		imgRepository.deleteById(imageId);
		return "redirect:/";
	}
	
}
