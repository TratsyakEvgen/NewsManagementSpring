package by.htp.ex.service;

import java.util.List;

import by.htp.ex.model.entity.News;

public interface NewsService {

	public List<News> getAll() throws ServiceException;

	public List<News> getNewsline(String locale) throws ServiceException;

	public List<News> getNewsCarousel(String locale) throws ServiceException;

	public News getNewsByIdandLocaleContent(int idNews, String locale) throws ServiceException;

	public News create(String username) throws ServiceException;

	public News get(int id) throws ServiceException;
	
	public News addImage(int idNews, int idImage) throws ServiceException;
	
	public News addUser(int idNews, int idUser) throws ServiceException;
	
	public News deleteImage(int idNews, int idImage) throws ServiceException;
	
	public void updateStatus(int idNews, boolean status) throws ServiceException;

}
