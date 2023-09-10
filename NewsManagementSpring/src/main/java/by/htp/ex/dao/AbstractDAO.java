package by.htp.ex.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;

public abstract class AbstractDAO<E> implements DAO<E> {

	protected Class<E> clazz;

	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public Optional<E> get(int id) throws DaoException {
		try {
			return Optional.ofNullable(getSession().get(clazz, id));
		} catch (Exception e) {
			throw new DaoException("Can't get by id", e);
		}
	}

	@Override
	public List<E> getAll() throws DaoException {
		try {
			return getSession().createQuery("from " + clazz.getName(), clazz).getResultList();
		} catch (Exception e) {
			throw new DaoException("Can't get all", e);
		}
	}

	@Override
	public void create(E entity) throws DaoException {
		try {
			getSession().persist(entity);
		} catch (Exception e) {
			throw new DaoException("Can't create", e);
		}
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
	public List<E> getListByFields(E entity) throws DaoException {
		try {
			return getQuery(entity).getResultList();
		} catch (Exception e) {
			throw new DaoException("Can't find list entity", e);
		}

	}

	@Override
	public Optional<E> getByFields(E entity) throws DaoException {
		try {
			return Optional.ofNullable(getQuery(entity).getSingleResultOrNull());
		} catch (Exception e) {
			throw new DaoException("Can't find entity", e);
		}
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Query<E> getQuery(E entity) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<E> critQuery = builder.createQuery(clazz);
		Root<E> root = critQuery.from(clazz);

		critQuery.select(root);
		critQuery.where(builder.and(getPredicatesByFieldsValues(builder, root, entity)));
		return session.createQuery(critQuery);
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
