package com.smartevent.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	
    boolean exists(ID id);
    
    T getById(ID id);
    
    List<T> getAll();

    void saveOrUpdate(T entity);
   
    void saveAll(List<T> entities);

    void delete(T entity);
    
    void delete(ID id);
}
