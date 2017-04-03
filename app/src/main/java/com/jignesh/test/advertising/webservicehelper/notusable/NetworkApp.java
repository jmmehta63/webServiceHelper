package com.jignesh.test.advertising.webservicehelper.notusable;

import android.app.Application;
import android.content.Context;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */
public class NetworkApp extends Application {
    private static NetworkApp _intance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        // Global.getInstance().init(getApplicationContext());
        // Global.getInstance().getDbObj();
    }
    public NetworkApp() {
        _intance = this;
    }
    public static Context getContext() {
        return _intance;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}
