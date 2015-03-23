/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.pages;

import com.smartevent.components.FacebookAuthenticationLink;
import com.smartevent.components.FacebookUserInformation;
import com.smartevent.services.FacebookService;
import com.smartevent.services.FacebookServiceInformation;
import java.io.IOException;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Mima
 */
public class Facebook {

    @Component
    private FacebookAuthenticationLink faceBookAuthentication;
    @Component
    private FacebookUserInformation userInformation;
    @Component
    private ActionLink logout;
    @Property
    @ActivationRequestParameter
    private String code;
    @Inject
    private FacebookService facebookService;
    @SessionState
    @Property
    private FacebookServiceInformation information;

    @SetupRender
    private void setup() throws IOException, OAuthSystemException,
            OAuthProblemException {
        if (code != null) {
            facebookService.getUserAccessToken(code,
                    information.getAccessToken());

        }
        code = null;
    }

    void onActionFromLogout() {
        information.setActionToken(null);
    }
}
