package by.htp.ex.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.model.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.http.HttpServletResponse;

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

	@GetMapping("/create")
	public String create(Model model, Principal principal) {
		try {
			News news = newsService.create(principal.getName());
			model.addAttribute("news", news);
			return "redirect: update/" + news.getId();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable int id) {
		try {
			model.addAttribute("news", newsService.get(id));
			return "updateNews";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@PostMapping("/addImage")
	public String addImage(Model model, @RequestParam int idNews, @RequestParam int idImage) {
		try {
			News news = newsService.addImage(idNews, idImage);
			model.addAttribute("news", news);
			return "redirect: update/" + news.getId();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@PostMapping("/deleteImage")
	public String deleteImage(Model model, @RequestParam int idNews, @RequestParam int idImage) {
		try {
			News news = newsService.deleteImage(idNews, idImage);
			model.addAttribute("news", news);
			return "redirect: update/" + news.getId();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@PostMapping("/addUser")
	public String addUser(Model model, @RequestParam int idNews, @RequestParam int idUser) {
		try {
			News news = newsService.addUser(idNews, idUser);
			model.addAttribute("news", news);
			return "redirect: update/" + news.getId();
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		}
	}

	@PostMapping("/updateStatus")
	public void updateStatus(Model model, @RequestParam int idNews, @RequestParam boolean status,
			HttpServletResponse response) throws IOException {
		try {
			newsService.updateStatus(idNews, status);
		} catch (ServiceException e) {
			ErrorHandler.handle(e, model, response);
		}
		response.setStatus(200);
	}

}
