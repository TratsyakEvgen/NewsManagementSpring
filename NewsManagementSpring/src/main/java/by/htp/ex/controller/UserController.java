package by.htp.ex.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.model.bean.UpdatePasswordData;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
@SessionAttributes("errorCodes")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private LogoutHandler logout;

	@PostMapping("/registration")
	public String registration(Model model, @ModelAttribute RegistrationData data) {
		try {
			service.registration(data);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: done";
	}

	@GetMapping("/admin/userList")
	public String getUserlist(Model model, @RequestParam(value = "select", required = false, defaultValue = "false") boolean select) {
		try {
			model.addAttribute("userList", service.getUserList());
			model.addAttribute("select", select);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "userList";
	}

	@PostMapping("/admin/updateRole")
	public void updateRole(Model model, @RequestParam("id") int id, @RequestParam("role") String role,
			HttpServletResponse response) throws IOException {
		try {
			service.updateRole(id, role);
		} catch (ServiceException e) {
			ErrorHandler.handle(e, model, response);
		}
		response.setStatus(200);
	}

	@PostMapping("/admin/updateStatus")
	public void updateStatus(Model model, @RequestParam("id") int id, @RequestParam("status") boolean status,
			HttpServletResponse response) throws IOException {
		try {
			service.updateStatus(id, status);
		} catch (ServiceException e) {
			ErrorHandler.handle(e, model, response);
		}
		response.setStatus(200);
	}

	@PostMapping("/auth/updatePassword")
	public String updatePassword(Model model, @ModelAttribute UpdatePasswordData data, Principal principal,
			HttpServletRequest request) {
		try {
			service.updatePassword(principal.getName(), data);
			logout.logout(request, null, null);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: /NewsManagementSpring/done";
	}

	@PostMapping("/auth/delete")
	public String delete(Model model, Principal principal, HttpServletRequest request) {
		try {
			service.delete(principal.getName());
			logout.logout(request, null, null);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: /NewsManagementSpring/done";
	}

}
