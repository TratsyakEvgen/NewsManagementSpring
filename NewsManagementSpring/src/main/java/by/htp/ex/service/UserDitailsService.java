package by.htp.ex.service;

import by.htp.ex.model.bean.UserDitailsData;
import by.htp.ex.model.entity.UserDitails;

public interface UserDitailsService {

	UserDitails get(String username) throws ServiceException;
	UserDitails update(String username, UserDitailsData data) throws ServiceException;

}
