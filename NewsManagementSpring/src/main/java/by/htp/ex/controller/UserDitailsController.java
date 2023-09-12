package by.htp.ex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.bean.UserDitailsData;
import by.htp.ex.model.entity.UserDitails;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserDitailsService;

@Controller
@RequestMapping("/userDitails/auth")
@SessionAttributes("errorCodes")
public class UserDitailsController {

	@Autowired
	private UserDitailsService service;

	@GetMapping("/account")
	public String getAccount(Model model, Principal principal) {
		try {
			UserDitails ditails = service.get(principal.getName());
			model.addAttribute("user", ditails);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "account";

	}

	@PostMapping("/update")
	public String update(Model model, @ModelAttribute UserDitailsData data, Principal principal) {
		try {
			service.update(principal.getName(), data);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: account";

	}

}
