/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.data;

/**
 *
 * @author Mima
 */
public enum Role {

    ANONYMOUS(0), USER(1), ADMIN(2);
    private int role;

    private Role(int role) {
        this.role = role;
    }
}
