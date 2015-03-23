/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartevent.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author mateja
 */
public class GenerateXMLService {

    public Document getItem(Object o) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document d;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            d = builder.newDocument();
            Class c = o.getClass();
            Element eltItem = d.createElement(c.getSimpleName());
            for (Method m : c.getMethods()) {
                if (m.getName().startsWith("get") && m.getName() != "getClass" && m.getReturnType().getName() != "java.util.List") {
                    try {
                        Element elt = d.createElement(m.getName().substring(3));
                        elt.appendChild(d.createTextNode("" + m.invoke(o, null)));
                        eltItem.appendChild(elt);

                    } catch (IllegalAccessException ex) {
                    } catch (InvocationTargetException ex) {
                    }
                }
            }

            d.appendChild(eltItem);
            d.normalizeDocument();

            // Returns the XML representation of this document.  
            return d;

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenerateXMLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public <T> Document getListaItems(List<T> lista) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document d;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            d = builder.newDocument();
            Element r = d.createElement("aerodromi");
            d.appendChild(r);
            for (T item : lista) {
                Class c = item.getClass();
                Element eltItem = d.createElement(c.getSimpleName());
                for (Method m : c.getMethods()) {
                    if (m.getName().startsWith("get") && m.getName() != "getClass" && m.getReturnType().getName() != "java.util.List") {
                        try {
                            Element elt = d.createElement(m.getName().substring(3));
                            elt.appendChild(d.createTextNode("" + m.invoke(item, null)));
                            eltItem.appendChild(elt);

                        } catch (IllegalAccessException ex) {
                        } catch (InvocationTargetException ex) {
                        }
                    }
                }
                r.appendChild(eltItem);
            }
            d.normalizeDocument();

            // Returns the XML representation of this document.  
            return d;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GenerateXMLService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
