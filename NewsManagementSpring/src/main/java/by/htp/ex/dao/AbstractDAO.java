package by.htp.ex.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;

@Repository
public abstract class AbstractDAO<E> implements DAO<E> {

	protected Class<E> clazz;

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public E get(int id) {
		return (E) getSession().get(clazz, id);
	}

	@Override
	public List<E> getAll() {
		return getSession().createQuery("from " + clazz.getName(), clazz).getResultList();
	}

	@Override
	public void create(E entity) {
		getSession().persist(entity);
	}

	@Override
	public void delete(E entity) {
		getSession().remove(entity);
	}

	@Override
	public void update(E entity) {
		getSession().merge(entity);
	}
	
	
	@Override
	public List<E> find(E entity, Collection<String> fetch) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<E> critQuery = builder.createQuery(clazz);
		Root<E> root = critQuery.from(clazz);
		
		fetch.forEach(s -> {root.fetch(s);});
		
		critQuery.select(root);
		critQuery.where(builder.and(getPredicatesByFieldsValues(builder, root, entity)));
		Query<E> query = session.createQuery(critQuery);
		
		return query.getResultList();

	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private <T> Predicate[] getPredicatesByFieldsValues(CriteriaBuilder builder, Root<E> root, T entity) {
		List<Predicate> predicates = null;
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				Object value = f.get(entity);
				if (!Modifier.isStatic(f.getModifiers()) && (value != null)
						&& (!Collection.class.isAssignableFrom(f.getType()))) {
					if (predicates == null) {
						predicates = new ArrayList<>();
					}
					predicates.add(builder.equal(root.get(f.getName()), value));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		Predicate[] arrayPredicates = null;
		
		if (predicates != null) {
			arrayPredicates = predicates.toArray(new Predicate[0]);
		}
		
		return arrayPredicates;
	}

}
