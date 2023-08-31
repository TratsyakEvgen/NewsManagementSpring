package by.htp.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;

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
		return null;
	}
	

}
