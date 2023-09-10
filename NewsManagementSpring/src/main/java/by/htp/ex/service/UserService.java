package by.htp.ex.service;

import java.util.List;

import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.model.bean.UpdatePasswordData;
import by.htp.ex.model.entity.User;

public interface UserService {
	
	void registration(RegistrationData registration) throws ServiceException;
	List<User> getUserList() throws ServiceException;
	void updateRole(int id, String role) throws ServiceException;
	void updateStatus(int id, boolean status) throws ServiceException;	
	void updatePassword(String username, UpdatePasswordData data) throws ServiceException;
	void delete(String username) throws ServiceException;

}
