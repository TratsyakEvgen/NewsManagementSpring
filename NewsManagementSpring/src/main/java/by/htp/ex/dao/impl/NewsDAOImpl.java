package by.htp.ex.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.entity.News;
import jakarta.persistence.NoResultException;

@Component
public class NewsDAOImpl extends AbstractDAO<News> implements NewsDAO {

	public NewsDAOImpl() {
		super.clazz = News.class;
	}

	@Override
	public Optional<List<News>> getActiveNewsFetchLocaleContent(String locale) throws DaoException {
		try {
			Query<News> query = getSession().createQuery(
					"FROM News n JOIN FETCH n.contents JOIN n.contents.locale l WHERE n.status = true AND l.locale = :locale",
					clazz);
			query.setParameter("locale", locale);
			return Optional.ofNullable(query.getResultList());
		} catch (Exception e) {
			throw new DaoException("Can't get active news and fetch locale content", e);
		}
	}

	@Override
	public Optional<List<News>> getActiveNewsFetchLocaleContentAndImages(String locale) throws DaoException {
		try {
			Optional<List<News>> listNews = getActiveNewsFetchLocaleContent(locale);
			listNews.ifPresent(news -> {
				Query<News> query = getSession().createQuery(
						"FROM News n JOIN n.contents.locale l LEFT JOIN FETCH n.images i WHERE n.status = true AND l.locale = :locale AND i.status = true",
						clazz);
				query.setParameter("locale", locale);
				news = query.getResultList();
			});
			return listNews;
		} catch (Exception e) {
			throw new DaoException("Can't get active news and fetch locale contents and images", e);
		}
	}

	@Override
	public Optional<News> getNewsByIdAndLocaleContentFetchAll(int idNews, String locale) throws DaoException {
		try {
			Query<News> query = getSession().createQuery(
					"FROM News n JOIN FETCH n.contents JOIN FETCH n.user JOIN n.contents.locale l WHERE n.id = :id AND l.locale = :locale",
					clazz);
			query.setParameter("id", idNews);
			query.setParameter("locale", locale);
			News news;
			try {
				news = query.getSingleResult();
			} catch (NoResultException e) {
				return Optional.ofNullable(null);
			}
			Hibernate.initialize(news.getImages());
			return Optional.ofNullable(news);
		} catch (Exception e) {
			throw new DaoException("Can't get news by id and locale content and fetch all", e);
		}
	}

	@Override
	public Optional<List<News>> getAllNewsFetchAll() throws DaoException {
		try {
			List<News> listNews = getSession().createQuery(
					"FROM News n LEFT JOIN FETCH n.contents c LEFT JOIN FETCH n.user LEFT JOIN FETCH c.locale", clazz)
					.getResultList();
			if (!listNews.isEmpty()) {
				listNews = getSession().createQuery("FROM News n LEFT JOIN FETCH n.images", clazz).getResultList();
			}
			return Optional.ofNullable(listNews);
		} catch (Exception e) {
			throw new DaoException("Can't get all and fetch all", e);
		}
	}

}
