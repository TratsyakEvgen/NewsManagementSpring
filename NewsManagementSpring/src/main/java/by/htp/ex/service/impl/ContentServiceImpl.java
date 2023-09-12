package by.htp.ex.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.ex.dao.ContentDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.LocaleDAO;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.model.bean.ContentData;
import by.htp.ex.model.entity.Content;
import by.htp.ex.model.entity.Locale;
import by.htp.ex.model.entity.News;
import by.htp.ex.service.ContentService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.Validator;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;

@Component
public class ContentServiceImpl implements ContentService {

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private ContentDAO contentDAO;

	@Autowired
	private LocaleDAO localeDAO;

	@Autowired
	private Validator validator;

	@Override
	@Transactional
	public void addContent(ContentData data) throws ServiceException {
		try {
			Optional<List<ErrorCode>> codes = validator.check(data);
			if (codes.isPresent()) {
				throw new ServiceException(codes.get());
			}
			Locale locale = Locale.builder().locale(data.getLocale()).build();
			locale = localeDAO.getByFields(locale).orElseThrow(() -> new ServiceException(ErrorCode.LOCALE_NOT_FOUND));

			News news = newsDAO.getNewsFetchAll(data.getIdNews())
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));

			final Locale localeFinal = locale;
			if (news.getContents().stream().anyMatch(c -> c.getLocale().equals(localeFinal))) {
				throw new ServiceException(ErrorCode.LOCALE_ALREADY_EXIST);
			}
			Content content = Content.builder().link(data.getLink()).title(data.getTitle()).locale(locale).news(news)
					.build();
			contentDAO.create(content);
		} catch (ValidationException e) {
			throw new ServiceException("Error validation data create content", e);
		} catch (DaoException e) {
			throw new ServiceException("Can't create content", e);
		}
	}

	@Override
	@Transactional
	public void updateContent(ContentData data) throws ServiceException {
		try {
			Optional<List<ErrorCode>> codes = validator.check(data);
			if (codes.isPresent()) {
				throw new ServiceException(codes.get());
			}
			Locale locale = Locale.builder().locale(data.getLocale()).build();
			locale = localeDAO.getByFields(locale).orElseThrow(() -> new ServiceException(ErrorCode.LOCALE_NOT_FOUND));

			News news = newsDAO.get(data.getIdNews())
					.orElseThrow(() -> new ServiceException(ErrorCode.NEWS_NOT_FOUND));

			final Locale localeFinal = locale;
			if (news.getContents().stream()
					.anyMatch(c -> c.getLocale().equals(localeFinal) && c.getId() != data.getId())) {
				throw new ServiceException(ErrorCode.LOCALE_ALREADY_EXIST);
			}
			Content content = contentDAO.get(data.getId())
					.orElseThrow(() -> new ServiceException(ErrorCode.CONTENT_NOT_FOUND));
			content.setLocale(localeFinal);
			content.setTitle(data.getTitle());
			content.setLink(data.getLink());
		} catch (ValidationException e) {
			throw new ServiceException("Error validation data update content", e);
		} catch (DaoException e) {
			throw new ServiceException("Can't create content", e);
		}
	}

	@Override
	@Transactional
	public void deleteContent(int id) throws ServiceException {
		try {
			contentDAO.delete(Content.builder().id(id).build());
		} catch (DaoException e) {
			throw new ServiceException("Can't delete content", e);
		}

	}

}
