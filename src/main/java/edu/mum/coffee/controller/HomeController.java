package edu.mum.coffee.controller;

import edu.mum.coffee.domain.FullUser;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Role;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.RoleRepository;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.UserService;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private PersonService personService;

	@Qualifier("roleRepository")
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping({"/", "/index", "/home"})
	public ModelAndView  homePage() {
		ModelAndView modelAndView= new ModelAndView("home");
		Collection<? extends GrantedAuthority> authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
		modelAndView.addObject("user", isAdmin ? "ADMIN" : "USER");
		return modelAndView;
	}


	@GetMapping({"/login"})
	public String login() {
		return "login";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(FullUser fullUser, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(fullUser.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			User user= new User();
			user.setEmail(fullUser.getEmail());
			user.setPassword(fullUser.getPassword());
			Person person= new Person();
			person.setEmail(fullUser.getEmail());
			person.setFirstName(fullUser.getFirstName());
			person.setLastName(fullUser.getLastName());
			person.setPhone(fullUser.getPhone());
			user.setPerson(person);
			Role userRole = roleRepository.findByRole(fullUser.getType());
			user.setRoles(new HashSet<>(Arrays.asList(userRole)));
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

}
