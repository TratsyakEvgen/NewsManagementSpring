package by.htp.ex.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.RoleDAO;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.model.bean.RegistrationData;
import by.htp.ex.model.entity.Role;
import by.htp.ex.model.entity.User;
import by.htp.ex.model.entity.UserDitails;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.Validator;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private Validator validator;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	@Transactional
	public void registration(RegistrationData data) throws ServiceException {
		try {
			Optional<List<ErrorCode>> codes = validator.check(data);
			if (codes.isPresent()) {
				throw new ServiceException(codes.get());
			}

			User user = User.builder().username(data.getUsername()).build();

			if (userDAO.getByFields(user).isPresent()) {
				throw new ServiceException(ErrorCode.LOGIN_ALREADY_EXIST);
			}

			user.setRole(roleDAO.getByFields(Role.builder().role("user").build()).get());
			user.setPassword(encoder.encode(data.getPassword()));
			user.setStatus(true);
			user.setDitails(UserDitails.builder().date(Date.valueOf(LocalDate.now())).email(data.getEmail())
					.name(data.getName()).surname(data.getSurname()).user(user).build());
			userDAO.create(user);

		} catch (ValidationException e) {
			throw new ServiceException("Error validation " + data.getClass().getName(), e);
		} catch (DaoException e) {
			throw new ServiceException("Can't register new user", e);
		}

	}

	@Override
	@Transactional
	public List<User> getUserList() throws ServiceException {
		try {
			return userDAO.getUsersFetchall();
		} catch (DaoException e) {
			throw new ServiceException("Can't get user list", e);
		}
	}

	@Override
	@Transactional
	public void updateRoleOrStatus(int id, String roleName, boolean status) throws ServiceException {
		User user = userDAO.get(id).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
		Role role = Role.builder().role(roleName).build();		
		try {
			role = roleDAO.getByFields(role).orElseThrow(() -> new ServiceException(ErrorCode.ROLE_NOT_FOUND));
			user.setRole(role);
			user.setStatus(status);
		} catch (DaoException e) {
			throw new ServiceException("Can't update role or status", e);
		}
		
		
		
	}

}
