package by.htp.ex.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDitailsDAO;
import by.htp.ex.model.entity.UserDitails;

@Repository
public class UserDitailsDAOImpl extends AbstractDAO<UserDitails> implements UserDitailsDAO {

	public UserDitailsDAOImpl() {
		super.clazz = UserDitails.class;
	}

	@Override
	public Optional<UserDitails> getByUsername(String username) throws DaoException {
		try {
			return Optional.ofNullable(
					getSession().createQuery("FROM UserDitails ud JOIN ud.user u WHERE u.username = :username", clazz)
							.setParameter("username", username).getSingleResultOrNull());
		} catch (Exception e) {
			throw new DaoException("Can't get user ditails by username", e);
		}
	}

}
