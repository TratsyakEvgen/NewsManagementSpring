package by.htp.ex.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.model.entity.News;
import jakarta.transaction.Transactional;

@Repository
public class NewsDAOImpl extends AbstractDAO<News> implements NewsDAO {

	public NewsDAOImpl() {
		super.clazz = News.class;
	}

	@Override
	public List<News> getActiveNewsFetchLocaleContent(String locale) throws DaoException {
		try {
			return getSession().createQuery(
					"FROM News n JOIN FETCH n.contents JOIN n.contents.locale l WHERE n.status = true AND l.locale = :locale",
					clazz).setParameter("locale", locale).getResultList();
		} catch (Exception e) {
			throw new DaoException("Can't get active news and fetch locale content", e);
		}
	}

	@Override
	public List<News> getActiveNewsFetchLocaleContentAndImages(String locale) throws DaoException {
		try {
			List<News> listNews = getActiveNewsFetchLocaleContent(locale);
			if (!listNews.isEmpty()) {
				listNews = getSession().createQuery(
						"FROM News n JOIN n.contents.locale l LEFT JOIN FETCH n.images i WHERE n.status = true AND l.locale = :locale AND i.status = true",
						clazz).setParameter("locale", locale).getResultList();
			}
			return listNews;
		} catch (Exception e) {
			throw new DaoException("Can't get active news and fetch locale contents and images", e);
		}
	}

	@Override
	public Optional<News> getNewsByIdAndLocaleContentFetchAll(int idNews, String locale) throws DaoException {
		try {
			News news = getSession().createQuery(
					"FROM News n JOIN FETCH n.contents JOIN FETCH n.userDitails JOIN n.contents.locale l WHERE n.id = :id AND l.locale = :locale",
					clazz).setParameter("id", idNews).setParameter("locale", locale).getSingleResultOrNull();
			if (news != null) {
				Hibernate.initialize(news.getImages());
			}
			return Optional.ofNullable(news);
		} catch (Exception e) {
			throw new DaoException("Can't get news by id and locale content and fetch all", e);
		}
	}

	@Override
	public List<News> getAllNewsFetchAll() throws DaoException {
		try {
			List<News> listNews = getSession().createQuery(
					"FROM News n LEFT JOIN FETCH n.contents c LEFT JOIN FETCH n.userDitails LEFT JOIN FETCH c.locale",
					clazz).getResultList();
			if (!listNews.isEmpty()) {
				listNews = getSession().createQuery("FROM News n LEFT JOIN FETCH n.images", clazz).getResultList();
			}
			return listNews;
		} catch (Exception e) {
			throw new DaoException("Can't get all and fetch all", e);
		}
	}

	@Override
	@Transactional
	public Optional<News> getNewsFetchAll(int id) throws DaoException {
		try {
			News news = getSession().createQuery(
					"FROM News n LEFT JOIN FETCH n.contents c LEFT JOIN FETCH n.userDitails LEFT JOIN FETCH c.locale WHERE n.id = :id",
					clazz).setParameter("id", id).getSingleResultOrNull();
			if (news != null) {
				Hibernate.initialize(news.getImages());
			}
			return Optional.ofNullable(news);
		} catch (Exception e) {
			throw new DaoException("Can't get news fetch all", e);
		}
	}

}
