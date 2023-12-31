package by.htp.ex.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<E>{
	
	public Optional<E> get(int id) throws DaoException;
	
	public List<E> getAll() throws DaoException;
	
	public void create(E entity) throws DaoException;
	
	public void delete(E entity) throws DaoException;
	
	public void update(E entity) throws DaoException;
	
	public List<E> getListByFields(E entity) throws DaoException;
	
	public Optional<E> getByFields(E entity) throws DaoException;
}
