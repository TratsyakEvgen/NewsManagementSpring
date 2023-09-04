package by.htp.ex.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
@SessionAttributes("errorCodes")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/registration")
	public String registration(Model model, @ModelAttribute("regData") RegistrationData data) {
		try {
			service.registration(data);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: done";
	}

	@GetMapping("/userList")
	public String getUserlist(Model model, @RequestParam("select") boolean select) {
		try {
			model.addAttribute("userList", service.getUserList());
			model.addAttribute("select", select);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "userList";
	}

	@PostMapping("/userUpdateRoleOrStatus")
	public void updateRoleOrStatus(Model model, @RequestParam("id") int id, @RequestParam("role") String role,
			@RequestParam("status") boolean status, HttpServletResponse response) throws IOException {
		try {
			service.updateRoleOrStatus(id, role, status);
		} catch (ServiceException e) {
			ErrorHandler.handle(e, model, response);
		}
		response.setStatus(200);
	}

}
