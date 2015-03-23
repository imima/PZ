/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.data;

/**
 *
 * @author Mima
 */
public enum PagesOrderEnum {
    HOME (0, "Index", "nav_index"),
    CREATE_EVENT (1, "CreateEvent", "nav_create_event_label"),
    CREATE_EVENT_AJAX (2, "CreateEventAjax", "nav_create_event_ajax_label"),
    EVENT_LIST (3, "ViewEvents", "nav_view_events_label"),
    EVENT_LIST_AJAX (4, "ViewEventsAjax", "nav_view_events_ajax_label"),
    SUPPORT(5, "Support", "nav_support_label"),
    FAQ(6, "FAQ", "nav_faq_label"),
    CONTACT(7, "Contact", "nav_contact_label"),
    ABOUT(8, "About", "nav_about_label"),
    ADMIN (9, "Admin", "nav_admin"),
    FACEBOOK (10, "Facebook", "nav_facebook");
    
    private int order;
    private String pageName;
    private String messageKey;

    private PagesOrderEnum() {
    }

    private PagesOrderEnum(int order, String pageName, String messageKey) {
        this.order = order;
        this.pageName = pageName;
        this.messageKey = messageKey;
    }
    
    private static PagesOrderEnum getPagesOrderEnum(String pageName) {
        for (PagesOrderEnum pageOrderEnum : PagesOrderEnum.values()) {
	    if(pageOrderEnum.pageName.equals(pageName)) { 
                return pageOrderEnum;
            }
        } 
        return null;
    }
    
    public static int getOrder(String pageName) {
	PagesOrderEnum pagesOrderEnum = getPagesOrderEnum(pageName);
	return pagesOrderEnum != null ? pagesOrderEnum.order : 0;
    }
 
    public static String getMessageKey(String pageName) {
        PagesOrderEnum pagesOrderEnum = getPagesOrderEnum(pageName);
	return pagesOrderEnum != null ? pagesOrderEnum.messageKey : "";
    }
    
    
}
