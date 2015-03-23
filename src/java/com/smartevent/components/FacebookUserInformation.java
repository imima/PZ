package com.smartevent.components;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.smartevent.services.FacebookServiceInformation;

public class FacebookUserInformation {

    @Property
    private User user;
    @SessionState
    @Property
    private FacebookServiceInformation information;

    @SetupRender
    public void setup() {
        FacebookClient facebookClient = new DefaultFacebookClient(information.getAccessToken());

        if (information.isLoggedIn()) {
            user = facebookClient.fetchObject("me", User.class);
        }
    }
}
