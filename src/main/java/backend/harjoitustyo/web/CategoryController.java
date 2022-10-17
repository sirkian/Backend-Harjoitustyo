package backend.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.harjoitustyo.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("categorylist")
	public String categorylist(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist";
	}

}
