package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "/users/index";
	}

	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("user", new User());
		return "users/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		userService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") long id) {
		model.addAttribute("user", userService.getUser(id));//todo: если User c указанным id нет? Валидация отсутствует, хотябы на паре методов - нужно обозначить
		return "users/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
		userService.update(id, user);
		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		userService.delete(id);
		return "redirect:/users";
		//todo: syntax.. лучше сразу привыкать к cleanCode, отступам.. лишние пробелы и др..
	}

	//todo: на фронте - очень хорошо, если .css подтягивается на страницу
	//todo: в POM-нике ..похоже подтянуто лишнего (все библиотеки используются?..)
}