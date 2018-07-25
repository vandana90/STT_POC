package com.stt.stt.common.application;

import android.app.Application;
import android.content.res.Configuration;

public class ApplicationClass extends Application {

    private static ApplicationClass sInstance;

    // Called when the application is starting, before any other application objects have been
    // created.
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    /**
     * get application instance
     * @return application instance
     */
    public static synchronized ApplicationClass getInstance() {
        if (sInstance == null) {
            sInstance = new ApplicationClass();
        }
        return sInstance;
    }


    // Called by the system when the device configuration changes while your component is running.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
