package com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.utn.tacs.tp2016c1g4.marvel_webapp.api.dao.exception.ManyResultsException;
import com.utn.tacs.tp2016c1g4.marvel_webapp.api.domain.Entity;

public abstract class InMemoryAbstractDao<T extends Entity, F extends SearchFilter<T>> implements Dao<T, F> {

	private Set<T> collection;
	private long nextId;

	public InMemoryAbstractDao() {
		super();
		this.collection = new HashSet<>();
		this.clear();
	}

	@Override
	public Set<T> getAll() {
		return new HashSet<>(collection);
	}

	@Override
	public Set<T> find(Collection<F> filters) {
		return find(filters, null);
	}

	@Override
	public Set<T> find(Collection<F> filters, Page page) {
		List<T> filtered = new ArrayList<>();

		for (T obj : collection) {
			boolean passes = true;

			for (F filter : filters) {
				passes = passes && passesFilter(filter, obj);

				if (!passes)
					break;
			}

			if (passes)
				filtered.add(fullClone(obj));
		}

		Collections.sort(filtered, new EntitySorter());

		List<T> pagedResults = new ArrayList<>();
		if (page != null) {
			int startPos = page.getPage() * page.getLimit();

			for (int i = 0; i < page.getLimit(); i++) {
				T elem = null;

				if (startPos + i < filtered.size()) {
					elem = filtered.get(startPos + i);
				}

				if (elem != null)
					pagedResults.add(elem);
			}
		} else {
			pagedResults = filtered;
		}

		return new HashSet<>(pagedResults);
	}

	protected abstract boolean passesFilter(F filter, T obj);

	protected abstract T fullClone(T from);

	protected abstract void cloneForUpdate(T from, T into);

	public T findForUpdate(Long id) {
		for (T obj : collection) {
			if (obj.getId().equals(id))
				return obj;
		}
		return null;
	}

	@Override
	public T findOne(Collection<F> filters) {
		Set<T> results = find(filters);

		if (results.size() > 1) {
			throw new ManyResultsException(results);
		}

		return results.size() == 1 ? results.iterator().next() : null;
	}

	@Override
	public boolean save(T obj) {
		boolean saved = false;
		if (obj.getId() != null)
			return saved;// ya tiene una id, existe en la coleccion
		obj.setId(this.nextId);
		saved = this.collection.add(obj);
		if (saved) {
			this.nextId++;
		}
		return saved;
	}

	@Override
	public boolean delete(T obj) {
		return this.collection.remove(obj);
	}

	@Override
	public boolean update(T obj) {
		T realObj = findForUpdate(obj.getId());
		if (realObj == null || obj == null)
			return false;
		cloneForUpdate(obj, realObj);
		return true;
	}

	public void clear() {
		this.collection.clear();
		this.nextId = 1;
	}

	private class EntitySorter implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			return (int) (o1.getId() - o2.getId());
		}

	}
}
