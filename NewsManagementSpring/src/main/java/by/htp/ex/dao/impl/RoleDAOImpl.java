package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.RoleDAO;
import by.htp.ex.model.entity.Role;

@Repository
public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

	public RoleDAOImpl() {
		super.clazz = Role.class;
	}

}
