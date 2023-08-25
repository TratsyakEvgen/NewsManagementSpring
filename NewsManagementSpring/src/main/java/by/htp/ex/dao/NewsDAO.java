package by.htp.ex.dao;

import java.util.List;
import java.util.Optional;

import by.htp.ex.entity.News;

public interface NewsDAO extends DAO<News>{
	public Optional<List<News>> getAllNewsFetchAll() throws DaoException;
	public Optional<List<News>> getActiveNewsFetchLocaleContent(String locale) throws DaoException;
	public Optional<List<News>> getActiveNewsFetchLocaleContentAndImages(String locale) throws DaoException;
	public Optional<News> getNewsByIdAndLocaleContentFetchAll(int idNews, String Locale) throws DaoException;
	
}
