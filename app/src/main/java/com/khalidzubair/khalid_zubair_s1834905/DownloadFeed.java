/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFeed {

    public DownloadFeed() {
    }

    public String fileDownload(String URLString) throws IOException {

        // variables
        URL url = new URL(URLString);
        String result = "";
        int response = -1;

        BufferedReader in = null;
        InputStream input = null;

        URLConnection connection = url.openConnection();
        try {
            // getting the URL
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setConnectTimeout(5000);
            httpConnection.connect();

            response = httpConnection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                Log.e("XML TAG", "Connection Found");

                // reading and adding the data to a string
                input = httpConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);

                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result = result + temp;
                }
            } else {
                Log.e("XML TAG", "Connection not found.");
            }
        } catch (IOException e) {
            Log.e("Could not connect.", e.toString());
        }
        return result;
    }
}
