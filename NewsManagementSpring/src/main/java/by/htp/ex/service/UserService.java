package by.htp.ex.service;

import by.htp.ex.model.bean.RegistrationData;

public interface UserService {
	
	void registration(RegistrationData registration) throws ServiceException;

}
