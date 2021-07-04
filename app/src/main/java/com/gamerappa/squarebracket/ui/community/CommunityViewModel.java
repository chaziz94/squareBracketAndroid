package com.gamerappa.squarebracket.ui.community;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommunityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CommunityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is community fragment, not finished");
    }

    public LiveData<String> getText() {
        return mText;
    }
}