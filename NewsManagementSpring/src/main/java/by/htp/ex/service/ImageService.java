package by.htp.ex.service;

import java.util.List;

import by.htp.ex.entity.Image;


public interface ImageService {

	List<Image> getAllImages() throws ServiceException;

	void add(String link) throws ServiceException;

	void update(Image image) throws ServiceException;

}
