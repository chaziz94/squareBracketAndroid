package com.gamerappa.squarebracket.ui.last_videos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gamerappa.squarebracket.R;
import com.gamerappa.squarebracket.API;
import com.gamerappa.squarebracket.VideoAdapter;
import com.gamerappa.squarebracket.databinding.FragmentLastVideosBinding;
import com.gamerappa.squarebracket.ui.EndlessScrollListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class lastVideosFragment extends Fragment {

    private lastVideosViewModel lastVideosViewModel;
    private FragmentLastVideosBinding binding;
    private ListView lv;

    public static final API api = new API();
    ArrayList<HashMap<String, String>> videosList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lastVideosViewModel =
                new ViewModelProvider(this).get(lastVideosViewModel.class);

        binding = FragmentLastVideosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) getView().findViewById(R.id.list_last_videos);
        lv.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                new AddLastVideos().execute((page - 1) * 30);

                return true;
            }
        });
        new lastVideosFragment.GetLastVideos().execute();
    }

    private class GetLastVideos extends AsyncTask<Void, Void, Void> {

        private ProgressBar spinner;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner = getView().findViewById(R.id.spinner_last_videos);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            videosList = api.getLastVideos(30, 0);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            final VideoAdapter lvadapter = new VideoAdapter(getActivity(), videosList);

            lv.setAdapter(lvadapter);
            lv.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
        }

    }

    private class AddLastVideos extends AsyncTask<Integer, Void, Void> {

        private ProgressBar spinner;
        ArrayList<HashMap<String, String>> videosListNew = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner = getView().findViewById(R.id.spinner_last_videos);
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Integer... arg0) {
            videosListNew = api.getLastVideos(30, arg0[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            videosList.addAll(videosListNew);
            lv.requestLayout();
            spinner.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}