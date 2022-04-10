/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSItem {
    private String title;
    private String description;
    private String link;
    private String publishDate;
    private String startDate;
    private String endDate;
    private String geoRSS;


    //Only includes date and not the time
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateOutFormat = new SimpleDateFormat("EEEE h:mm a (MMM d)");


    //Getters

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getPublishDateFormatted() {
        try {
            if (publishDate != null) {
                Date date = dateInFormat.parse(publishDate);
                return dateOutFormat.format(date);
            } else {
                return "There is no date in the RSS feed";
            }
        } catch (ParseException e) {
            return "There is no date in the RSS feed";
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getGeoRSS() {
        return geoRSS;
    }

    //Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setGeoRSS(String geoRSS) {
        this.geoRSS = geoRSS;
    }

}
