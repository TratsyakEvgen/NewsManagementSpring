package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.model.entity.User;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO{
	public UserDAOImpl() {
		super.clazz = User.class;
	}
}
