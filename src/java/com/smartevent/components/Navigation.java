/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.components;

import com.smartevent.data.PagesOrderEnum;
import com.smartevent.entities.User;
import com.smartevent.services.security.ProtectedPage;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentClassResolver;

/**
 *
 * @author Mima
 */
public class Navigation {

    @Property
    private Map.Entry<String, String> page;
    @Inject
    private ComponentResources resources;
    @Inject
    private ComponentClassResolver componentClassResolver;
    @Inject
    private Messages messages;
    @SessionState
    @Property
    private User ssoUser;
    private boolean ssoUserExists;
    
    private static TreeMap<String, String> pagesMapSorted;
    
    static class PageMapValueComparator implements Comparator<String> {

        Map<String, String> base;
        public PageMapValueComparator(Map<String, String> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (PagesOrderEnum.getOrder(a) < PagesOrderEnum.getOrder(b)) {
                return -1;
            } else {
                return 1;
            } 
        }
    }
    
    public Set<Map.Entry<String, String>> getPages() {
        Map<String, String> pagesMap= new HashMap<String, String>();
        for (String pageName : componentClassResolver.getPageNames()) {         
            if (!pageName.startsWith("core") && !pageName.startsWith("tapx") && !pageName.startsWith("hibernate")) {
                if (hasAccess(componentClassResolver.resolvePageNameToClassName(pageName))) {
                    pagesMap.put(pageName, messages.get(PagesOrderEnum.getMessageKey(pageName)));
                }
            }
        }
        // return sorted pages by order from enum file
        pagesMapSorted = new TreeMap<String,String>(new PageMapValueComparator(pagesMap));
        pagesMapSorted.putAll(pagesMap);
        return pagesMapSorted.entrySet();
    }
    
    public boolean isCurrentPage(String pageName) {
        return resources.getContainer().getComponentResources().getPageName().equals(pageName);
    }
    
    private boolean hasAccess(String page) {
        try {
            Class clazz = Class.forName(page);
            if (clazz.getAnnotations().length == 0) return true;
            if (!ssoUserExists) return false;
            return ((ProtectedPage) clazz.getAnnotation(ProtectedPage.class)).role().equals(ssoUser.getRole()) ? true : false;
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException for " + page);
            Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
