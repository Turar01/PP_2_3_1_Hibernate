package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String listUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping("/add")
	public String addUserForm(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/users/";
	}

	@GetMapping("/edit")
	public String editUserForm(@RequestParam Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping("/edit")
	public String editUser(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/users/";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam Long id) {
		userService.deleteUser(id);
		return "redirect:/users/";
	}
}
