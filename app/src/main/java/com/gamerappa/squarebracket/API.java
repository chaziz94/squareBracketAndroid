package com.gamerappa.squarebracket;

import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class API {
    String sharedPrefFile = "sqrconfig";
    String TAG = FirstActivity.class.getSimpleName();
    // TODO: Config file to server sh*t

    public ArrayList<HashMap<String, String>> getLastVideos() {
        ArrayList<HashMap<String, String>> videosList = new ArrayList<>();
        HttpHandler sh = new HttpHandler();

        String jsonStr = sh.makeServiceCall("https://squarebracket.veselcraft.ru/api/v1/get_videos.php?limit=30");

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("videos");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString("id");
                    String name = c.getString("title");
                    String email = c.getString("description");
                    JSONObject phone = c.getJSONObject("author");
                    String mobile = phone.getString("username");

                    HashMap<String, String> video = new HashMap<>();

                    video.put("id", id);
                    video.put("name", name);
                    video.put("email", email);
                    video.put("mobile", mobile);

                    // adding contact to contact list
                    videosList.add(video);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");

        }
        return videosList;
    }
}
