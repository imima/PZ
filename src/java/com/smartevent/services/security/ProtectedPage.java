/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartevent.services.security;

import com.smartevent.data.Role;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *
 * @author Mima
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtectedPage {
    Role role() default Role.ANONYMOUS;
}
