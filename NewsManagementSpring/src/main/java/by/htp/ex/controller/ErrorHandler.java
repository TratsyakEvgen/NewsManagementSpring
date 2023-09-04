package by.htp.ex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorHandler {

	public static String handle(ServiceException e, Model model) {
		model.addAttribute("errorCodes", getCodes(e));
		return "redirect: /NewsManagementSpring/error";
	}

	public static void handle(ServiceException e, Model model, HttpServletResponse response) throws IOException {
		model.addAttribute("errorCodes", getCodes(e));
		response.sendRedirect("/NewsManagementSpring/error");
	}

	public static String handle(ErrorCode errorCode, Model model) {
		List<ErrorCode> codes = new ArrayList<>();
		codes.add(errorCode);
		model.addAttribute("errorCodes", codes);
		return "redirect: /NewsManagementSpring/error";
	}

	private static List<ErrorCode> getCodes(ServiceException e) {
		return e.getCodes().orElseGet(() -> {

			// this should be a logger
			e.printStackTrace();

			List<ErrorCode> listCodes = new ArrayList<>();
			listCodes.add(ErrorCode.INTERAL_SERVER_ERROR);
			return listCodes;
		});
	}
}
