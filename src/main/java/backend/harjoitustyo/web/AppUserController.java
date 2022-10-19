package backend.harjoitustyo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
