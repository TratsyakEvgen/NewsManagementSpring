package by.htp.ex.service;

import java.util.List;
import by.htp.ex.entity.News;

public interface NewsService {
	
	public List<News> getAll() throws ServiceException;

	public List<News> getNewsline(String locale) throws ServiceException;
	
	public List<News> getNewsCarousel(String locale) throws ServiceException;
	
	public News getNewsByIdandLocaleContent(int idNews, String locale) throws ServiceException;

}
