/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CurrentIncident extends Fragment {

    SimpleAdapter adapter;
    ListView itemsListView;
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

        itemsListView = view.findViewById(R.id.itemsListView);


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

    @SuppressLint("SetTextI18n")
    public void updateDisplay() {
        if (itemsView == null) {
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Unable to get RSS feed";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }


        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (RSSItem item : itemsView) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("publishDate", ("Publication Date: " + item.getPublishDateFormatted()));
            map.put("title", ("Title: " + item.getTitle()));
            map.put("description", ("Description: " + item.getDescription()));
            map.put("link", ("Link: " + item.getLink()));
            map.put("geoRSS", ("Coordinates: " + item.getGeoRSS()));
            list.add(map);
        }

        int resource = R.layout.listview_incident;

        String[] from = {"publishDate", "title", "description", "link", "geoRSS"};
        int[] to = {R.id.publishDateTextView, R.id.titleTextView,
                R.id.descriptionTextView, R.id.linkTextView, R.id.geoTextView};

        if (getActivity() != null) {
            adapter = new SimpleAdapter(getActivity(), list, resource, from, to);
            itemsListView.setAdapter(adapter);
        }



    }

}