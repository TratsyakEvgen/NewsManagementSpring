package by.htp.ex.dao.impl;

import org.springframework.stereotype.Component;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.model.entity.Content;

@Component
public class ContentDAOImpl extends AbstractDAO<Content>{
	
	public ContentDAOImpl(){
		super.clazz = Content.class;
	}
}
