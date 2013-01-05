/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import twitter4j.URLEntity;

import java.net.URL;

/**
 *
 * @author john
 */
public class LinkToHtmlConverter {
    
    private static final String LINK_START = "<a href=\"";
    private static final String LINK_MID = "\" >";
    private static final String LINK_END = "</a>";
    
    private static String linkify(String displayURL, URL expandedURL ) {
        return LINK_START + expandedURL.toExternalForm() + LINK_MID + displayURL + LINK_END;
    }
    
    public static String replaceLinksWithHtml(String text, URLEntity[] urls) {
        String currentHtml = text;
        for(URLEntity url : urls) {
            currentHtml = currentHtml.replaceAll(url.getURL().toExternalForm(), linkify(url.getDisplayURL(), url.getExpandedURL()));
        }
        return currentHtml;
    }
    
    
}
