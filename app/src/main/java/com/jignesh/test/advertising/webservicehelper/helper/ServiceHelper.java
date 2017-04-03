package com.jignesh.test.advertising.webservicehelper.helper;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.jignesh.test.advertising.webservicehelper.R;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class ServiceHelper extends WSClient {

    public static final String COMMON_ERROR = "Could not connect to server!\n Please try again.";
    private boolean isShowProgressbar = true;

    public ServiceHelper(Context context) {
        super(context);
    }

    public ServiceHelper(Context context, boolean isShowProgressbar) {
        super(context);
        this.isShowProgressbar = isShowProgressbar;
    }
    @Override
    public void postData(String URL, String parameters, webserviceHelper.WebServiceCallback callback) {
        CallService calling = new CallService(URL, parameters, "POST", callback);
        calling.execute();
    }
    @Override
    public void getData(String QueryURL, webserviceHelper.WebServiceCallback callback) {
        CallService calling = new CallService(QueryURL, "", "GET", callback);
        calling.execute();
    }
    private android.os.Handler progressHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    try {
                        if (pd != null) {
                            pd.dismiss();
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (WindowManager.BadTokenException ei) {
                        ei.printStackTrace();
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };
    ProgressDialog pd;
    public class CallService extends AsyncTask<Void, Void, Void> {
        String URL;
        String post_data;
        String Method;
        boolean IsResponseFail = false;
        String result;
        webserviceHelper.WebServiceCallback callback;
        public CallService(String url, String params, String MethodType, webserviceHelper.WebServiceCallback callback_service) {
            URL = url;
            post_data = params;
            Method = MethodType;
            callback = callback_service;
        }
        @Override
        protected void onPreExecute() {
            try {
                if (isShowProgressbar) {
                    pd = new ProgressDialog(getContext(), R.style.SpinnerStyle);
                    pd.setCancelable(false);
                    pd.show();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (WindowManager.BadTokenException ei) {
                ei.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected Void doInBackground(Void... params) {
            if (NetworkConnectivity.isConnected(getContext())) {
                StringBuilder builder = new StringBuilder();
                OkHttpClient client = new OkHttpClient();
                client.setConnectTimeout(60, TimeUnit.SECONDS);
                client.setReadTimeout(60, TimeUnit.SECONDS);
                client.setWriteTimeout(60, TimeUnit.SECONDS);
                Request request = null;

                if (Method.equalsIgnoreCase("GET")) {
                    final MediaType JSON
                            = MediaType.parse("application/json; charset=utf-8");
                    request = new Request.Builder()
                            .url(URL)
                            .addHeader("Content-Type", "application/json")
                            .build();
                } else {
                    final MediaType JSON
                            = MediaType.parse("application/json; charset=utf-8");
                    if (post_data.length() > 0) {
                        String queryString = post_data;
                        RequestBody body = RequestBody.create(JSON, queryString);
                        request = new Request.Builder()
                                .url(URL)
                                .post(body)
                                .build();
                    } else {
                        RequestBody body = RequestBody.create(JSON, "");
                        request = new Request.Builder()
                                .url(URL)
                                .post(body)
                                .build();
                    }
                }
                try {
                    Response response = client.newCall(request).execute();
                    if (response.code() == 200 || response.code() == 500 || response.code() == 417) {
                        result = builder.append(response.body().string()).toString();
                        IsResponseFail = false;
                    } else {
                       /* result = builder.append ( response.body ().string () ).toString ();
                        if ( result == null || result.equalsIgnoreCase ( "" ) )
                            result = COMMON_ERROR;*/
                        IsResponseFail = true;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    IsResponseFail = true;
                }
            } else {
                IsResponseFail = true;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressHandler.sendEmptyMessage(0);
            if (!IsResponseFail) {
                Log.e("Response:", result);
                if (result != null && result.contains("{")) {
                    callback.onSuccess(result);
                } else {
                    callback.onFailure(result);
                }
            } else {
                callback.onFailure(COMMON_ERROR);
//                callback.onFailure ( result );
            }
            if (isShowProgressbar) {
                pd.dismiss();
            }
        }
    }
}