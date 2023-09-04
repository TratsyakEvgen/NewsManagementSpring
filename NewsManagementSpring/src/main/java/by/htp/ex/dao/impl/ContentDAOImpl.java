package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.ContentDAO;
import by.htp.ex.model.entity.Content;

@Repository
public class ContentDAOImpl extends AbstractDAO<Content> implements ContentDAO{
	
	public ContentDAOImpl(){
		super.clazz = Content.class;
	}
}
