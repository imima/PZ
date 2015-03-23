/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartevent.components;

import com.smartevent.entities.User;
import com.smartevent.services.business.UserService;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */

@Import(stylesheet={"context:css/bootstrap/bootstrap.css",
    //"context:css/grid960/960.css",
    //"context:css/grid960/reset.css",
   // "context:css/grid960/text.css", 
    "context:css/styles.css"})

public class Layout {

    @Inject
    private UserService userService;
    
    @Inject
    private ComponentResources resources;
    @Parameter(required = true, defaultPrefix = "literal")
    private String pageTitle;

    @Persist
    @Property
    private User login;
    @SessionState
    @Property
    private User ssoUser;
    @Property
    private boolean ssoUserExists;
    @Inject
    private Block loginBlock, loggedinBlock;
    
    @InjectComponent
    private Zone timeSpentOnSite;
    @Persist
    @Property
    private int timeSpent;
    
    public String getPageTitle() {
        return pageTitle;
    }

    private String getPageName() {
        return resources.getContainer().getClass().getName();
    }
    
    public Object getActiveBlock() {
        return ssoUserExists ? loggedinBlock : loginBlock;
    }

    public void onActionFromLogin(){
        ssoUser = userService.getByUsernameAndPassword(login.getUsername(), login.getPassword());
    }
    
    public Object onActionFromLogout(){
        ssoUser = null;
        timeSpent = 0;
        return null;
    }
    
    @OnEvent(value = "refresh", component = "timeSpentOnSite")
    public Object onZoneRefresh() {
        if(ssoUser != null) {
            timeSpent++; 
            return timeSpentOnSite.getBody();
        }
        return null;    
    }
    
}
