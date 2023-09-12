package by.htp.ex.service;

import by.htp.ex.model.bean.ContentData;

public interface ContentService {
	public void addContent(ContentData data) throws ServiceException;

	public void updateContent(ContentData data) throws ServiceException;

	public void deleteContent(int id) throws ServiceException;

}
