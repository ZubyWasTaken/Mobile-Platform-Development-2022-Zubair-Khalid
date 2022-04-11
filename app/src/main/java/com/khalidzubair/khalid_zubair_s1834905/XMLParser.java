/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class XMLParser {
    RSSItem item;

    private ArrayList<RSSItem> items;

    public ArrayList<RSSItem> getItems() {
        return items;
    }

    public void pullParser(String reader) {

        items = new ArrayList<>();

        try {
            XmlPullParserFactory xmlPPFactory = XmlPullParserFactory.newInstance();
            xmlPPFactory.setNamespaceAware(true);
            XmlPullParser xmlPP = xmlPPFactory.newPullParser();
            xmlPP.setInput(new StringReader(reader));
            int eventType = xmlPP.getEventType();
            FormatData helper = new FormatData();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start of document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xmlPP.getName().equals("item")) {
                        item = new RSSItem();
                        xmlPP.nextTag();
                        if (xmlPP.getName().equals("title")) {
                            xmlPP.next();
                            item.setTitle(xmlPP.getText());
                            xmlPP.nextTag();
                            xmlPP.nextTag();
                        }
                        if (xmlPP.getName().equals("description")) {
                            xmlPP.next();
                            item.setDescription(helper.getDescription(xmlPP.getText()));
                            String[] dates = helper.getDates(xmlPP.getText());

                            if (dates != null) {
                                item.setStartDate(helper.longDateToShort(dates[0]));
                                item.setEndDate(helper.longDateToShort(dates[1]));
                            }
                            xmlPP.nextTag();
                            xmlPP.nextTag();
                        }
                        if (xmlPP.getName().equals("link")) {
                            xmlPP.next();
                            item.setLink(xmlPP.getText());
                            xmlPP.nextTag();
                            xmlPP.nextTag();
                        }
                        if (xmlPP.getName().equals("point")) {
                            xmlPP.next();
                            item.setGeoRSS(xmlPP.getText());
                            xmlPP.nextTag();
                            xmlPP.nextTag();
                        }
                        xmlPP.nextTag();
                        xmlPP.nextTag();
                        xmlPP.nextTag();
                        xmlPP.nextTag();

                        if (xmlPP.getName().equals("pubDate")) {
                            xmlPP.next();
                            item.setPublishDate(xmlPP.getText());
                            Log.v("check data", xmlPP.getText());
                            xmlPP.nextTag();
                            xmlPP.nextTag();
                        }
                        items.add(item);
                    }
                }
                eventType = xmlPP.next();
            }
        } catch (XmlPullParserException e) {
            Log.e("SomeTag", "Parsing error" + e.toString());
        } catch (IOException e) {
            Log.e("SomeTag", "IO error occurred during parsing");
        }
        Log.e("XMLParser", "End of the document");

    }


}
