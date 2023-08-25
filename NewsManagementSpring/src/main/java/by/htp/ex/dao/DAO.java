package by.htp.ex.dao;

import java.util.Collection;
import java.util.List;



public interface DAO<E>{
	
	public E get(int id);
	
	public List<E> getAll();
	
	public void create(E entity);
	
	public void delete(E entity);
	
	public void update(E entity);
	
	public List<E> find(E entity, Collection<String> fetch);
	

}
