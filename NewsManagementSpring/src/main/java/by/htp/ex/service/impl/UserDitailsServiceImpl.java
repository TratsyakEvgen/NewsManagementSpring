package by.htp.ex.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDitailsDAO;
import by.htp.ex.model.bean.UserDitailsData;
import by.htp.ex.model.entity.UserDitails;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserDitailsService;
import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.Validator;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;

@Service
public class UserDitailsServiceImpl implements UserDitailsService {
	@Autowired
	private UserDitailsDAO dao;
	@Autowired
	private Validator validator;

	@Override
	@Transactional
	public UserDitails get(String username) throws ServiceException {
		try {
			return dao.getByUsername(username).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
		} catch (DaoException e) {
			throw new ServiceException("Can't get user ditails by username", e);
		}

	}

	@Override
	@Transactional
	public void update(String username, UserDitailsData data) throws ServiceException {

		try {
			Optional<List<ErrorCode>> codes = validator.check(data);
			if (codes.isPresent()) {
				throw new ServiceException(codes.get());
			}

			UserDitails ditails = dao.getByUsername(username)
					.orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
			ditails.setName(data.getName());
			ditails.setSurname(data.getSurname());
			ditails.setEmail(data.getEmail());
		} catch (ValidationException e) {
			throw new ServiceException("Error validation " + data.getClass().getName(), e);
		} catch (DaoException e) {
			throw new ServiceException("Can't update user ditails", e);
		}
	}

}
