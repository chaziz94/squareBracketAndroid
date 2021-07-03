package com.gamerappa.squarebracket.ui.last_videos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gamerappa.squarebracket.FirstActivity;
import com.gamerappa.squarebracket.MainActivity;
import com.gamerappa.squarebracket.R;
import com.gamerappa.squarebracket.API;
import com.gamerappa.squarebracket.databinding.FragmentLastVideosBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class lastVideosFragment extends Fragment {

    private lastVideosViewModel lastVideosViewModel;
    private FragmentLastVideosBinding binding;
    private ListView lv;

    public static final API api = new API();

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
        new lastVideosFragment.GetLastVideos().execute();
    }

    private class GetLastVideos extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> videosList = new ArrayList<>();
        private ProgressBar spinner;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner = getView().findViewById(R.id.spinner_last_videos);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            videosList = api.getLastVideos();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(
                    getContext(), videosList,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);
            lv.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}