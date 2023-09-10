package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.model.entity.Role;
import by.htp.ex.model.entity.User;

public interface UserDAO extends DAO<User> {

	List<User> getSecretUsersFetchAll() throws DaoException;

	int updateStatus(int id, boolean status) throws DaoException;

	int updateStatus(String usermane, boolean status) throws DaoException;

	int updateRole(int id, Role role) throws DaoException;

	int updatePassword(String usermane, String password) throws DaoException;
}
