package by.htp.ex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.model.entity.User;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
	public UserDAOImpl() {
		super.clazz = User.class;
	}

	@Override
	public List<User> getUsersFetchall() throws DaoException {
		
		try {
			return getSession()
					.createQuery("SELECT new User(u.id,u.status,r,d) FROM User u JOIN u.role r JOIN u.ditails d", clazz)
					.getResultList();
		} catch (Exception e) {
			throw new DaoException("Can't get all users fetch all", e);
		}
	}
}
