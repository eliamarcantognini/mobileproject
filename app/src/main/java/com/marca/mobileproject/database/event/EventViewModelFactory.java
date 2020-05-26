//package com.marca.mobileproject.database.event;
//
//import android.app.Application;
//
//import androidx.lifecycle.ViewModel;
//import androidx.lifecycle.ViewModelProvider;
//
//public class EventViewModelFactory implements ViewModelProvider.Factory {
//
//    private Application mApplication;
//    private String mParam;
//
//
//    public EventViewModelFactory(Application application, String param) {
//        mApplication = application;
//        mParam = param;
//    }
//
//
//    @Override
//    public <T extends ViewModel> T create(Class<T> modelClass) {
//        return (T) new EventViewModelFactory(mApplication, mParam);
//    }
//}
