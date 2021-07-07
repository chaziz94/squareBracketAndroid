package com.gamerappa.squarebracket;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamerappa.squarebracket.ui.DownloadImageTask;

public class VideoAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    public VideoAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);

        TextView title = (TextView)vi.findViewById(R.id.video_title);
        TextView author = (TextView)vi.findViewById(R.id.video_author);
        TextView description = (TextView)vi.findViewById(R.id.video_description);
        ImageView preview = (ImageView)vi.findViewById(R.id.video_preview);

        HashMap<String, String> video = new HashMap<String, String>();
        video = data.get(position);

        // Setting all values in listview
        title.setText(video.get("title"));
        author.setText(video.get("author"));
        description.setText(video.get("description"));
        new DownloadImageTask(preview).execute(video.get("preview"));
        return vi;
    }
}