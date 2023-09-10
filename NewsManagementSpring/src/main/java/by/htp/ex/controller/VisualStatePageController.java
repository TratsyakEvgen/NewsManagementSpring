package by.htp.ex.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import by.htp.ex.util.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
@SessionAttributes("errorCodes")
public class VisualStatePageController {

	@GetMapping("/")
	public String getIndexPage() {
		return "basePage";
	}


	@GetMapping("/loginError")
	public String getLogin(Model model) {
		return ErrorHandler.handle(ErrorCode.INCORRECT_LOGIN_OR_PASSWORD, model);
	}

	@GetMapping("/{path}")
	public String getElement(@PathVariable String path) {
		return path;
	}

	@GetMapping("/error")
	public String getError(SessionStatus sessionStatus, HttpServletResponse response) {
		sessionStatus.setComplete();
		response.setStatus(418);
		return "error";
	}

}
