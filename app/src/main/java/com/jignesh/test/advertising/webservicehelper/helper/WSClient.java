package com.jignesh.test.advertising.webservicehelper.helper;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */

import android.content.Context;

/**
 * Created by mayank.sarda on 7/18/2016.
 */
abstract class WSClient {

    WSClient() {

    }

    public Context getContext() {
        return mContext;
    }

    private Context mContext;

    public WSClient(Context context) {
        this.mContext = context;
    }

    public abstract void postData(String URL, String parameters, webserviceHelper.WebServiceCallback callback);

    public abstract void getData(String QueryURL, webserviceHelper.WebServiceCallback callback);
}
