package com.gamerappa.squarebracket.ui.last_videos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class lastVideosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public lastVideosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is last_videos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}