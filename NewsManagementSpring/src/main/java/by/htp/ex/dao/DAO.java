package by.htp.ex.dao;

import java.util.List;

public interface DAO<E>{
	
	public E get(int id);
	
	public List<E> getAll() throws DaoException;
	
	public void create(E entity) throws DaoException;
	
	public void delete(E entity);
	
	public void update(E entity);
	
	public List<E> findByFields(E entity) throws DaoException;
	

}
