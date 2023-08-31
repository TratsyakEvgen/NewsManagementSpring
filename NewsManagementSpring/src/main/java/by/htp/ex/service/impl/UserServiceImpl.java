package by.htp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.UserDAO;
import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.Validator;
import jakarta.xml.bind.ValidationException;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void registration(RegistrationData data) throws ServiceException {
		try {
			List<ErrorCode> codes = Validator.check(data);
			if (codes != null) {
				throw new ServiceException(codes);
			}
			
		} catch (ValidationException e) {
			throw new ServiceException("Error validation " + data.getClass(), e);
		} 


		
	}

}
