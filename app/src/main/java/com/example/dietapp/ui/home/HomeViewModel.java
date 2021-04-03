package com.example.dietapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//this class returns a string value when the HomeFragment shows up
public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Who We Are?"); //set string value
    }

    public LiveData<String> getText() {
        return mText;
    } //return string value
}