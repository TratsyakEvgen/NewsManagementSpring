package by.htp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.NewsDAO;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import jakarta.transaction.Transactional;

@Component
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO dao;

	@Override
	@Transactional
	public List<News> getNewsline(String locale) throws ServiceException {
		try {
			return dao.getActiveNewsFetchLocaleContent(locale)
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get newsline by locale " + locale, e);
		}

	}

	@Override
	@Transactional
	public List<News> getNewsCarousel(String locale) throws ServiceException {
		try {
			return dao.getActiveNewsFetchLocaleContentAndImages(locale)
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get news carousel by locale " + locale, e);
		}
	}

	@Override
	@Transactional
	public News getNewsByIdandLocaleContent(int idNews, String locale) throws ServiceException {
		try {
			return dao.getNewsByIdAndLocaleContentFetchAll(idNews, locale)
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get news by locale and locale content " + locale, e);
		}
	}

	@Override
	@Transactional
	public List<News> getAll() throws ServiceException {
		try {
			return dao.getAllNewsFetchAll()
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get all news", e);
		}
	}

}
