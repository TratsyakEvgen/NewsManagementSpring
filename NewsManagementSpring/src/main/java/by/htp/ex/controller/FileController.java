package by.htp.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.htp.ex.service.FileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.impl.DirectoryName;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

@Controller
@RequestMapping("/files")
@SessionAttributes("errorCodes")
public class FileController {

	@Autowired
	private ServletContext context;

	@Autowired
	private FileSystemService fileSystemService;

	@GetMapping("/get")
	public String getFiles(Model model, @RequestParam("dir") String dir) {
		try {
			String realPart = context.getRealPath("");
			String directory = DirectoryName.valueOf(dir.toUpperCase()).getDir();
			String dirPath = context.getRealPath(directory);
			List<String> files = fileSystemService.getAllFiles(realPart, dirPath);
			model.addAttribute("files", files);
			model.addAttribute("dir", dir);
			return "filesView";
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		} catch (IllegalArgumentException | NullPointerException e) {
			return ErrorHandler.handle(new ServiceException(e), model);
		}
	}

	@PostMapping(path = "/upload")
	public String upload(Model model, @RequestParam("dir") String dir, @RequestParam("file") Part file) {
		try {
			String directory = DirectoryName.valueOf(dir.toUpperCase()).getDir();
			String dirPath = context.getRealPath(directory);
			fileSystemService.create(dirPath, file);
			return "redirect: get?dir=" + dir;
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		} catch (IllegalArgumentException | NullPointerException e) {
			return ErrorHandler.handle(new ServiceException(e), model);
		}
	}
	
	
	@PostMapping(path = "/delete")
	public String delete(Model model, @RequestParam("dir") String dir, @RequestParam("link") String link) {
		try {
			String realPart = context.getRealPath(link);
			fileSystemService.delete(realPart);
			return "redirect: get?dir=" + dir;
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		} catch (IllegalArgumentException | NullPointerException e) {
			return ErrorHandler.handle(new ServiceException(e), model);
		}
	}
	
	@PostMapping(path = "/update")
	public String update(Model model, @RequestParam("dir") String dir, @RequestParam("link") String link, @RequestParam("file") Part file) {
		try {
			String realPart = context.getRealPath(link);
			fileSystemService.update(realPart, file);
			return "redirect: get?dir=" + dir;
		} catch (ServiceException e) {
			return ErrorHandler.handle(e, model);
		} catch (IllegalArgumentException | NullPointerException e) {
			return ErrorHandler.handle(new ServiceException(e), model);
		}
	}

}
