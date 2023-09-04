package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.UserDitailsDAO;
import by.htp.ex.model.entity.UserDitails;

@Repository
public class UserDitailsDAOImpl extends AbstractDAO<UserDitails> implements UserDitailsDAO {
	
	public UserDitailsDAOImpl() {
		super.clazz = UserDitails.class;
	}

}
