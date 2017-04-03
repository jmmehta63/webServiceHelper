package com.jignesh.test.advertising.webservicehelper.helper;

import android.content.Context;
import android.util.Log;

import com.jignesh.test.advertising.webservicehelper.R;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */

public class webserviceHelper {

    private Context mContext;
    static String url;
    String parameter;
    private boolean isShowProgressbar = true;
    private ServiceHelper helper;

    public webserviceHelper ( Context context ) {
        this.mContext = context;
    }
    public webserviceHelper ( Context context, boolean isShowProgressbar ) {
        this.mContext = context;
        this.isShowProgressbar = isShowProgressbar;
    }
    public void isShowProgressbar ( boolean isShowProgressbar ) {
        this.isShowProgressbar = isShowProgressbar;
    }
    public void callPostAPI ( String postUrl, String postParams, WebServiceCallback callback ) {
        url = postUrl;

        Log.e ( "URL_POST=====>", postUrl );
        Log.e ( "POST DATA=====>", postParams );

        if ( ! ConnectionDetector.isInternetAvailable ( mContext ) ) {
            callback.onFailure ( mContext.getString(R.string.internet_unavailability_msg) );
            return;
        }

        if ( isShowProgressbar ) {
            helper = new ServiceHelper ( mContext );
        } else {
            helper = new ServiceHelper ( mContext, isShowProgressbar );
        }
        helper.postData ( postUrl, postParams, callback );

//        DoPOSTCall(mContext, postUrl, postParams, callback);
//        AsyncClient client = new AsyncClient(mContext);
//        client.postData(postUrl, postParams, callback);

    }

    public void callGetAPI ( String queryURL, WebServiceCallback callback ) {
        Log.e ( "URL_GET=====>", queryURL );
        if ( ! ConnectionDetector.isInternetAvailable ( mContext ) ) {
            callback.onFailure (  mContext.getString(R.string.internet_unavailability_msg)  );
            return;
        }
        if ( isShowProgressbar ) {
            helper = new ServiceHelper ( mContext );
        } else {
            helper = new ServiceHelper ( mContext, isShowProgressbar );
        }

        helper.getData ( queryURL, callback );
//        DoGETCall(mContext, queryURL, callback);
//        AsyncClient client = new AsyncClient(mContext);
//        client.getData(queryURL, callback);
    }

    public interface WebServiceCallback {
        public void onSuccess ( String response );

        public void onFailure ( String errorMessage );
    }

}

