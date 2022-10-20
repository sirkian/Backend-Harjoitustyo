package backend.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.harjoitustyo.domain.Category;
import backend.harjoitustyo.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("categories")
	public String categorylist(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}
	
	@GetMapping("/categories/{categoryId}")
	public String getCategory(@PathVariable("categoryId") Long categoryId, Model model) {
	    model.addAttribute("category", categoryRepository.findCategoryByCategoryId(categoryId));
	    return "category";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/add/category")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "add-category";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add/category/save")
	public String saveCategory(@Valid Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-category";
		}
		categoryRepository.save(category);
		return "redirect:../../categories";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/category/{categoryId}")
	public String editCategory(@PathVariable("categoryId") Long categoryId, Model model) {
		model.addAttribute("category", categoryRepository.findById(categoryId));
		return "edit-category";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/edit/category/save")
	public String saveEditedCategory(@Valid Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit-category";
		}
		categoryRepository.save(category);
		return "redirect:../../categories";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/category/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") Long categoryId) {
		categoryRepository.deleteById(categoryId);
		return "redirect:../../categories";
	}

}
