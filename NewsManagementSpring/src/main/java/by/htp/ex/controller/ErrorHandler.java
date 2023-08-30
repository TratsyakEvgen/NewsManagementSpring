package by.htp.ex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;

public class ErrorHandler {

	private final static String ERROR_PATH = "redirect: /NewsManagementSpring/error";

	public static String handle(ServiceException e, Model model) {
		List<ErrorCode> codes = e.getCodes().orElseGet(() -> {
			
			//this should be a logger
			e.printStackTrace();

			List<ErrorCode> listCodes = new ArrayList<>();
			listCodes.add(ErrorCode.INTERAL_SERVER_ERROR);
			return listCodes;
		});
		model.addAttribute("errorCodes", codes);

		return ERROR_PATH;
	}
	
	public static String handle(ErrorCode errorCode, Model model) {
		List<ErrorCode> codes = new ArrayList<>();
		codes.add(errorCode);
		model.addAttribute("errorCodes", codes);		
		return ERROR_PATH;		
	}
}
