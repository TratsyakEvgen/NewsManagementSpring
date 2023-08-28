package by.htp.ex.dao.impl;

import org.springframework.stereotype.Repository;

import by.htp.ex.dao.AbstractDAO;
import by.htp.ex.dao.ImageDAO;
import by.htp.ex.entity.Image;

@Repository
public class ImageDAOImpl extends AbstractDAO<Image> implements ImageDAO {
	
	public ImageDAOImpl() {
		super.clazz = Image.class;
	}
	

}
