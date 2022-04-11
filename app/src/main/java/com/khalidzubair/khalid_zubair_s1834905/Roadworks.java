/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Roadworks extends Fragment {

    private ListView itemsListView;
    SimpleAdapter adapter;
    private ArrayList<RSSItem> itemsView;

    public Roadworks() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planned_roadworks, container, false);
        DownloadFeed downloader = new DownloadFeed();
        XMLParser parser = new XMLParser();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Roadworks");

        itemsListView = view.findViewById(R.id.itemsListView);

        try {
            String STRING_URL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
            String input = downloader.fileDownload(STRING_URL);
            parser.pullParser(input);
            itemsView = parser.getItems();
            Roadworks.this.updateDisplay();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void updateDisplay() {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (RSSItem item : itemsView) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("publishDate", ("Publication Date: " + item.getPublishDateFormatted()));
            map.put("title", ("Title: " + item.getTitle()));
            map.put("startEndDate", ("Start Date: " + item.getStartDate() + " - End Date: " + item.getEndDate()));
            map.put("description", ("Description: " + item.getDescription()));
            map.put("link", ("Link: " + item.getLink()));
            map.put("geoRSS", ("Coordinates: " + item.getGeoRSS()));
            list.add(map);
        }

        int resource = R.layout.listview_roadworks;

        String[] from = {"publishDate", "title", "startEndDate", "description", "link", "geoRSS"};
        int[] to = {R.id.publishDateTextView, R.id.titleTextView, R.id.startEndDateTextView,
                R.id.descriptionTextView, R.id.linkTextView, R.id.geoTextView};

        if (getActivity() != null) {
            adapter = new SimpleAdapter(getActivity(), list, resource, from, to);
            itemsListView.setAdapter(adapter);
        }
    }
}