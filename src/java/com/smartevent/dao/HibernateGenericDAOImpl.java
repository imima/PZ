package com.smartevent.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

public abstract class HibernateGenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @Inject
    private Session hibernate;

    protected Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public HibernateGenericDAOImpl() {
	this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
	return persistentClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(ID id) {
	T entity = null;
	try {
	    entity = (T) hibernate.get(getPersistentClass(), id);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
	return entity;
    }

    @Override
    public boolean exists(ID id) {
	return getById(id) != null ? true : false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
	List<T> entities = null;
	try {
	    entities = (List<T>) hibernate.createQuery("from " + persistentClass.getName()).list();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
	return entities;
    }

    @Override
    public void saveOrUpdate(T entity) {
	try {
	    hibernate.saveOrUpdate(entity);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
    
    @Override
    public void saveAll(List<T> entities){
	try {
	    for(T entity : entities)
		 hibernate.saveOrUpdate(entity);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public void delete(T entity) {
	try {
	    hibernate.delete(entity);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(ID id) {
	try {
	    T result = (T) hibernate.get(getPersistentClass(), id);
	    hibernate.delete(result);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}