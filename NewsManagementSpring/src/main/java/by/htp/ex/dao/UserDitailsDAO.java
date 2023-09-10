package by.htp.ex.dao;

import java.util.Optional;

import by.htp.ex.model.entity.UserDitails;

public interface UserDitailsDAO extends DAO<UserDitails> {
	
	Optional<UserDitails> getByUsername(String username) throws DaoException;

}
