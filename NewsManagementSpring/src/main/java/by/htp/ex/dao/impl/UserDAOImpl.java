package by.htp.ex.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.model.entity.Role;
import by.htp.ex.model.entity.User;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
	public UserDAOImpl() {
		super.clazz = User.class;
	}

	@Override
	public List<User> getSecretUsersFetchAll() throws DaoException {
		try {
			return getSession()
					.createQuery("SELECT new User(u.id,u.status,r,d) FROM User u JOIN u.role r JOIN u.ditails d", clazz)
					.getResultList();
		} catch (Exception e) {
			throw new DaoException("Can't get all users fetch all", e);
		}
	}

	@Override
	public int updateStatus(int id, boolean status) throws DaoException {
		try {
			return getSession().createMutationQuery("UPDATE User SET status = :status WHERE id = :id")
					.setParameter("status", status).setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			throw new DaoException("Can't update status", e);
		}
	}

	@Override
	public int updateRole(int id, Role role) throws DaoException {
		try {
			return getSession().createMutationQuery("UPDATE User SET role = :role WHERE id = :id")
					.setParameter("role", role).setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			throw new DaoException("Can't update status", e);
		}

	}

	@Override
	public int updatePassword(String username, String password) throws DaoException {
		try {
			return getSession().createMutationQuery("UPDATE User SET password = :password WHERE username = :username")
					.setParameter("password", password).setParameter("username", username).executeUpdate();
		} catch (Exception e) {
			throw new DaoException("Can't update status", e);
		}
	}
	
	@Override
	public int updateStatus(String username, boolean status) throws DaoException {
		try {
			return getSession().createMutationQuery("UPDATE User SET status = :status WHERE username = :username")
					.setParameter("status", status).setParameter("username", username).executeUpdate();
		} catch (Exception e) {
			throw new DaoException("Can't update status", e);
		}
	}

}
