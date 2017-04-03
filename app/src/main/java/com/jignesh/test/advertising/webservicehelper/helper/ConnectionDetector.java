package com.jignesh.test.advertising.webservicehelper.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */

public class ConnectionDetector { private Context _context;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    public static boolean isInternetAvailable(Context _context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}