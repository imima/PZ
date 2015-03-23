package com.smartevent.rest.filter;

import org.apache.tapestry5.ioc.services.PerthreadManager;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Filter;
 
/**
 * 
 * @author Aleksandar
 */
public class CleanupFilter extends Filter {
    private PerthreadManager perthreadManager;
 
    public CleanupFilter(Context ctx, PerthreadManager perthreadManager) {
        super(ctx);
        this.perthreadManager = perthreadManager;
    }
 
    @Override
    protected void afterHandle(Request request, Response response) {
        perthreadManager.cleanup();
    }
}
