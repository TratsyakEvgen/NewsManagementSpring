package by.htp.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.bean.ContentData;
import by.htp.ex.service.ContentService;
import by.htp.ex.service.ServiceException;

@Controller
@RequestMapping("/contents/admin")
@SessionAttributes("errorCodes")
public class ContentController {

	@Autowired
	private ContentService service;

	@PostMapping("/addContent")
	public String addContent(Model model, @ModelAttribute ContentData data) {
		try {
			service.addContent(data);
			return "redirect: /NewsManagementSpring/news/update/" + data.getIdNews();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}
	
	@PostMapping("/updateContent")
	public String updateContent(Model model, @ModelAttribute ContentData data) {
		try {
			service.updateContent(data);
			return "redirect: /NewsManagementSpring/news/update/" + data.getIdNews();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@PostMapping("/deleteContent")
	public String deleteContent(Model model, @RequestParam("idNews") int idNews, @RequestParam("idContent") int idContent) {
		try {
			service.deleteContent(idContent);
			return "redirect: /NewsManagementSpring/news/update/" + idNews;
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

}
