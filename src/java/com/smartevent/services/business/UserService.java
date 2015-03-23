/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.services.business;

import com.smartevent.entities.User;

/**
 *
 * @author Mima
 */
public interface UserService {
    
    User getByUsernameAndPassword(String username, String password);
}
