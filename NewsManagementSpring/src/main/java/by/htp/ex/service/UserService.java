package by.htp.ex.service;

import java.util.List;

import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.model.entity.User;

public interface UserService {
	
	void registration(RegistrationData registration) throws ServiceException;
	List<User> getUserList() throws ServiceException;
	void updateRoleOrStatus(int id, String role, boolean status) throws ServiceException;

}
