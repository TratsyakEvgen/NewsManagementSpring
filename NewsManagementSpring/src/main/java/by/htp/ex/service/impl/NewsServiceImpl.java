package by.htp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.NewsDAO;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import jakarta.transaction.Transactional;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO dao;

	@Override
	@Transactional
	public List<News> getNewsline(String locale) throws ServiceException {
		try {
			List<News> news = dao.getActiveNewsFetchLocaleContent(locale);
			if (news.isEmpty()) {
				throw new ServiceException(ErrorCode.NEWS_NOT_FOUND);
			}
			return news;
		} catch (DaoException e) {
			throw new ServiceException("Can't get newsline by locale " + locale, e);
		}

	}

	@Override
	@Transactional
	public List<News> getNewsCarousel(String locale) throws ServiceException {
		try {
			List<News> news = dao.getActiveNewsFetchLocaleContentAndImages(locale);
			if (news.isEmpty()) {
				throw new ServiceException(ErrorCode.NEWS_NOT_FOUND);
			}
			return news;
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
			List<News> news = dao.getAllNewsFetchAll();
			if (news.isEmpty()) {
				throw new ServiceException(ErrorCode.NEWS_NOT_FOUND);
			}
			return news;
		} catch (DaoException e) {
			throw new ServiceException("Can't get all news", e);
		}
	}

}
