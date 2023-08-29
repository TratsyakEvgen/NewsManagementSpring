package by.htp.ex.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.entity.User;
import jakarta.transaction.Transactional;

@Service
public class UserSecurity implements UserDetailsService {

	@Autowired
	private UserDAO dao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername(username);
		try {
			user = dao.findByFields(user).stream().findFirst().orElseThrow(() -> new UsernameNotFoundException(username));
		} catch (DaoException e) {
			throw new UsernameNotFoundException(username);
		}
		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles(user.getRole().getRole()).build();
		return userDetails;
	}
}
