package backend.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.harjoitustyo.domain.AppUser;
import backend.harjoitustyo.domain.AppUserRepository;
import backend.harjoitustyo.domain.SignupForm;

@Controller
public class AppUserController {
	
	@Autowired
	private AppUserRepository userRepository;
	
	@RequestMapping(value = "login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("users")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/user/{userId}")
	public String editUser(@PathVariable("userId") Long userId, Model model) {
		model.addAttribute("user", userRepository.findById(userId));
		return "edit-user";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/edit/user/save")
	public String saveEditedUser(@Valid AppUser user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit-user";
		}
		userRepository.save(user);
		return "redirect:../../users";
	}
		
	@PostMapping("saveuser")
	public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if(signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String password = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String passwordHash = bc.encode(password);
				AppUser user = new AppUser();
				user.setUsername(signupForm.getUsername());
				user.setRole("USER");
				user.setEmail(signupForm.getEmail());
				user.setPasswordHash(passwordHash);
				if (userRepository.findByUsername(signupForm.getUsername()) == null) {
					if (userRepository.findByEmail(signupForm.getEmail()) == null) {
						userRepository.save(user);
					} else {
						bindingResult.rejectValue("email", "err.email", "Account already exists with that email!");
						return "signup";
					}
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already taken!");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passwordCheck", "Passwords doesn't match!");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:login";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/user/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {
		userRepository.deleteById(userId);
		return "redirect:../../users";
	}
}
