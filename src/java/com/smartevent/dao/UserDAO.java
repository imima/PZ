/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.dao;

import com.smartevent.entities.User;

/**
 *
 * @author Mima
 */
public interface UserDAO extends GenericDAO<User, Integer> {
    
    User getByUsernameAndPassword(String username, String password);
}
