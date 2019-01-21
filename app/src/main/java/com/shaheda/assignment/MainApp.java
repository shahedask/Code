package com.shaheda.assignment;

import android.app.Application;

import com.shaheda.assignment.network.IApi;
import com.shaheda.assignment.network.ApiService;

public class MainApp extends Application {

    private IApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        if (api == null) {
            ApiService apiService = new ApiService();
            api = apiService.createApiService();
        }
    }

    public IApi getApiInterface() {
        return api;
    }
}
