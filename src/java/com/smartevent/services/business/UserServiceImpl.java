/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.services.business;

import com.smartevent.dao.UserDAO;
import com.smartevent.entities.User;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDao;

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }
    
    
}
