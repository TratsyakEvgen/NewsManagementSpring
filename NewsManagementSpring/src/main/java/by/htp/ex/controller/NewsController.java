package by.htp.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

@Controller
@RequestMapping("/news")
@SessionAttributes("errorCodes")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/newsline")
	public String getNewsline(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model) {
		try {
			List<News> news = newsService.getNewsline(locale);
			model.addAttribute("listNews", news);
			return "newsline";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@GetMapping("/newsCarousel")
	public String getNewsCarousel(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model) {
		try {
			List<News> news = newsService.getNewsCarousel(locale);
			model.addAttribute("listNews", news);
			return "newsCarousel";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@GetMapping("/newsManagement")
	public String getAllNews(Model model) {
		try {
			List<News> news = newsService.getAll();
			model.addAttribute("listNews", news);
			return "newsManagement";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@GetMapping("/newsView")
	public String getNews(@CookieValue(name = "locale", defaultValue = "en") String locale, Model model,
			@RequestParam("id") int id) {
		try {
			News news = newsService.getNewsByIdandLocaleContent(id, locale);
			model.addAttribute("news", news);
			return "newsView";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}

	}

}
