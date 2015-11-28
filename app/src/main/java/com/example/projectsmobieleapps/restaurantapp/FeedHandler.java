package com.example.projectsmobieleapps.restaurantapp;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.jar.Attributes;

/**
 * Created by MichielAdmin on 28/11/2015.
 */
public class FeedHandler extends DefaultHandler {
    private Feed feed;
    private FeedItem item;

    private boolean feedNameHasBeenRead = false;
    private boolean feedVicinityHasBeenRead = false;

    private boolean isName = false;
    private boolean isVicinity = false;
    private boolean isLatitude = false;
    private boolean isLongitude = false;

    public Feed getFeed() {
        return feed;
    }

    @Override
    public void startDocument() throws SAXException {
        feed = new Feed();
        item = new FeedItem();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
            throws SAXException {
        if (qName.equals("result")) {
            item = new FeedItem();
            return;
        }
        else if (qName.equals("name")) {
            isName = true;
            return;
        }
        else if (qName.equals("vicinity")) {
            isVicinity = true;
            return;
        }
        else if (qName.equals("lat")) {
            isLatitude = true;
            return;
        }
        else if (qName.equals("lng")) {
            isLongitude = true;
            return;
        }
    }

    @Override
    public void endElement(String namesaceURI, String localName, String qName) throws SAXException {
        if (qName.equals("result")) {
            feed.addItem(item);
            return;
        }
    }
}
