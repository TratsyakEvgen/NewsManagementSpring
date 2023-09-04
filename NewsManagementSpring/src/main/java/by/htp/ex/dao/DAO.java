package by.htp.ex.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<E>{
	
	public Optional<E> get(int id);
	
	public List<E> getAll() throws DaoException;
	
	public void create(E entity) throws DaoException;
	
	public void delete(E entity);
	
	public void update(E entity);
	
	public List<E> getListByFields(E entity) throws DaoException;
	
	public Optional<E> getByFields(E entity) throws DaoException;
}
