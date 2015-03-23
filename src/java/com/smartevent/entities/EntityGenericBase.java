package com.smartevent.entities;

import java.io.Serializable;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.tapestry5.beaneditor.NonVisual;

@MappedSuperclass
public abstract class EntityGenericBase<T> implements Serializable {

    private static final long serialVersionUID = -1589092525258655051L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @NonVisual
    private T id;
    
    public EntityGenericBase() {}
    
    public EntityGenericBase(T id) {
	this.id = id;
    }
    
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
	return new HashCodeBuilder(17, 31).append(id).toHashCode();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	return new EqualsBuilder().append(id, ((EntityGenericBase) obj).id).isEquals();
    }
}
