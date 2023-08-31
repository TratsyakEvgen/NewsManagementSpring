package by.htp.ex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.ImageDAO;
import by.htp.ex.model.entity.Image;
import by.htp.ex.service.ImageService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.ErrorCode;
import jakarta.transaction.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDAO dao;

	@Override
	@Transactional
	public List<Image> getAllImages() throws ServiceException {
		try {
			List<Image> result = dao.getAll();
			if (result.isEmpty()) {
				throw new ServiceException(ErrorCode.IMAGES_NOT_FOUND);
			}
			return result;
		} catch (DaoException e) {
			throw new ServiceException("Can't get all images", e);
		}
	}

	@Override
	@Transactional
	public void add(String link) throws ServiceException {
		Image image = new Image();
		image.setLink(link);
		try {
			if (!dao.findByFields(image).isEmpty()) {
				throw new ServiceException(ErrorCode.IMAGE_ALREADY_EXIST);
			}
			image.setStatus(true);
			dao.create(image);
		} catch (DaoException e) {
			throw new ServiceException("Can't add image", e);
		}

	}

	@Override
	@Transactional
	public void update(Image image) throws ServiceException {
		Image searchImage = new Image();
		searchImage.setLink(image.getLink());
		try {
			long count = dao.findByFields(searchImage).stream().filter(t -> t.getId() != image.getId()).count();
			if (count > 0) {
				throw new ServiceException(ErrorCode.IMAGE_ALREADY_EXIST);
			}
			dao.update(image);
		} catch (DaoException e) {
			throw new ServiceException("Can't update image", e);
		}

	}

}
