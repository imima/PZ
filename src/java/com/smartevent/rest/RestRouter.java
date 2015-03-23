
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.rest;

import com.smartevent.rest.resource.EventResource;
import javax.servlet.ServletContext;
import org.apache.tapestry5.TapestryFilter;
import org.apache.tapestry5.ioc.Registry;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestRouter extends Application {

    private Registry registry;

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    public synchronized Restlet createRoot() {

        if (this.registry == null) {
            ServletContext ctx = (ServletContext) getContext().getAttributes()
                    .get("org.restlet.ext.servlet.ServletContext");
            registry = (Registry) ctx
                    .getAttribute(TapestryFilter.REGISTRY_CONTEXT_NAME);
            if (registry == null) {
                throw new RuntimeException(
                        "Failed to obtain a reference to Tapestry`s registry");
            }
        }
        Router router = new Router(getContext());
        router.attach("/event", EventResource.class);
        return router;
    }

    public <T> T getService(Class<T> service) {
        return registry.getService(service);
    }
}
