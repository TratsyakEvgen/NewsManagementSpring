package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.model.entity.User;


public interface UserDAO extends DAO<User>{
	
	List<User> getUsersFetchall() throws DaoException;

}
