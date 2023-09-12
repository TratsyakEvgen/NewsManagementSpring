package by.htp.ex.service.impl;

import java.sql.Timestamp;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.ImageDAO;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.dao.UserDitailsDAO;
import by.htp.ex.model.entity.Image;
import by.htp.ex.model.entity.News;
import by.htp.ex.model.entity.UserDitails;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import jakarta.transaction.Transactional;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private UserDitailsDAO userDitailsDAO;

	@Autowired
	private ImageDAO imageDAO;	

	@Override
	@Transactional
	public List<News> getNewsline(String locale) throws ServiceException {
		try {
			List<News> news = newsDAO.getActiveNewsFetchLocaleContent(locale);
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
			List<News> news = newsDAO.getActiveNewsFetchLocaleContentAndImages(locale);
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
	public News getNews(int idNews, String locale) throws ServiceException {
		try {
			return newsDAO.getNewsByIdAndLocaleContentFetchAll(idNews, locale)
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get news by locale and locale content " + locale, e);
		}
	}

	@Override
	@Transactional
	public List<News> getAll() throws ServiceException {
		try {
			List<News> news = newsDAO.getAllNewsFetchAll();
			if (news.isEmpty()) {
				throw new ServiceException(ErrorCode.NEWS_NOT_FOUND);
			}
			return news;
		} catch (DaoException e) {
			throw new ServiceException("Can't get all news", e);
		}
	}

	@Override
	@Transactional
	public int create(String username) throws ServiceException {
		try {
			UserDitails details = userDitailsDAO.getByUsername(username)
					.orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
			News news = News.builder().dateTime(new Timestamp(System.currentTimeMillis())).userDitails(details)
					.status(false).build();
			newsDAO.create(news);
			return news.getId();
		} catch (DaoException e) {
			throw new ServiceException("Can't create news", e);
		}

	}

	@Override
	@Transactional
	public News get(int id) throws ServiceException {
		try {
			return newsDAO.getNewsFetchAll(id).orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get news by id", e);
		}
	}

	@Override
	@Transactional
	public void addImage(int idNews, int idImage) throws ServiceException {
		try {
			Image image = imageDAO.get(idImage).orElseThrow(() -> new ServiceException(ErrorCode.IMAGES_NOT_FOUND));
			News news = newsDAO.get(idNews).orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
			List<Image> images = news.getImages();
			if (images.contains(image)) {
				throw new ServiceException(ErrorCode.IMAGE_ALREADY_EXIST);
			}
			news.getImages().add(image);
		} catch (DaoException e) {
			throw new ServiceException("Can't add image in news", e);
		}
	}

	@Override
	@Transactional
	public void deleteImage(int idNews, int idImage) throws ServiceException {
		try {
			Image image = imageDAO.get(idImage).orElseThrow(() -> new ServiceException(ErrorCode.IMAGES_NOT_FOUND));
			News news = newsDAO.get(idNews).orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
			news.getImages().remove(image);
		} catch (DaoException e) {
			throw new ServiceException("Can't delete image in news", e);
		}
	}

	@Override
	@Transactional
	public void addUser(int idNews, int idUser) throws ServiceException {
		try {
			UserDitails user = userDitailsDAO.get(idUser)
					.orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
			News news = newsDAO.get(idNews).orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
			news.setUserDitails(user);
		} catch (DaoException e) {
			throw new ServiceException("Can't add user in news", e);
		}
	}

	@Override
	@Transactional
	public void updateStatus(int idNews, boolean status) throws ServiceException {
		try {
			News news = newsDAO.get(idNews).orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));
			news.setStatus(status);
		} catch (DaoException e) {
			throw new ServiceException("Can't update status in news", e);
		}

	}

	

}
