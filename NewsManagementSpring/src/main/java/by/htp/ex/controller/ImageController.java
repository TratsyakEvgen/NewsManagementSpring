package by.htp.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.entity.Image;
import by.htp.ex.service.ImageService;
import by.htp.ex.service.ServiceException;

@Controller
@RequestMapping("/images")
@SessionAttributes("errorCodes")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/get")
	private String getAll(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model) {
		try {
			List<Image> images = imageService.getAllImages();
			model.addAttribute("images", images);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "viewImages";
	}
	
	
	@PostMapping("/add")
	private String add(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model, @RequestParam("link") String link) {
		try {
			imageService.add(link);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: get";
	}
	
	@PostMapping("/update")
	private String update(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model, @ModelAttribute("image_{id}") Image image) {
		try {			
			imageService.update(image);
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
		return "redirect: get";
	}
	
	
	
	
	

}
