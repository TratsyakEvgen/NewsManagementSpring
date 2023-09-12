package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.LocaleDAO;
import by.htp.ex.model.entity.Locale;

@Repository
public class LocaleDAOImpl extends AbstractDAO<Locale> implements LocaleDAO{
	
	public LocaleDAOImpl() {
		super.clazz = Locale.class;
	}

}
