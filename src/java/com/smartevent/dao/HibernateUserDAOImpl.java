/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.dao;

import com.smartevent.entities.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mima
 */
public class HibernateUserDAOImpl extends HibernateGenericDAOImpl<User, Integer> implements UserDAO {

    @Inject
    private Session hibernate;

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return (User) hibernate.createCriteria(User.class).add(
                Restrictions.and(
                    Restrictions.eq("username", username), 
                    Restrictions.eq("password", password)))
                .uniqueResult();   
    }
    
}
