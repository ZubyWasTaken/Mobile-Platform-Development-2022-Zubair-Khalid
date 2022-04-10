package com.khalidzubair.khalid_zubair_s1834905;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class CurrentIncident extends Fragment {

    private TextView title;
    private TextView description;
    private TextView link;
    private TextView georss;
    private TextView publishDate;

    private ArrayList<RSSItem> itemsView;

    public CurrentIncident() {
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
        View view = inflater.inflate(R.layout.fragment_current_incident, container, false);

        DownloadFeed downloader = new DownloadFeed();
        XMLParser parser = new XMLParser();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Current Incidents");

        title = (TextView) view.findViewById(R.id.titleTextView);
        description = (TextView) view.findViewById(R.id.descriptionTextView);
        link = (TextView) view.findViewById(R.id.linkTextView);
        georss = (TextView) view.findViewById(R.id.geoTextView);
        publishDate = (TextView) view.findViewById(R.id.publishDateTextView);

        try {
            String STRING_URL = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
            String input = downloader.fileDownload(STRING_URL);
            parser.pullParser(input);
            itemsView = parser.getItems();
            CurrentIncident.this.updateDisplay();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void updateDisplay() {
        if (itemsView == null) {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Unable to get RSS feed";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        for (RSSItem it : itemsView) {
            title.setText("Title: " + it.getTitle());
            description.setText("Description: " + it.getDescription());
            link.setText("Link: " + it.getLink());
            georss.setText("Coordinates: " + it.getGeoRSS());
            publishDate.setText("Publication Date: " + it.getPublishDate());
        }


    }

}