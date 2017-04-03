package com.jignesh.test.advertising.webservicehelper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.jignesh.test.advertising.webservicehelper.helper.webserviceHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.buttonGet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(getApplicationContext());
            }
        });
    }

    private void getData(Context context) {
        try {
            webserviceHelper helper = new webserviceHelper(this);
            if (helper == null) {
                helper = new webserviceHelper(this);
            }
            helper.isShowProgressbar(true);
            String url = "http://10.0.2.2/test_api/register";
            JSONObject jobj = new JSONObject();
            jobj.put("username", "JigneshMehta");
            jobj.put("password", "Jignesh");

            // Always Pass String Of Json Object

            helper.callPostAPI(url, jobj.toString(), new webserviceHelper.WebServiceCallback() {

                @Override
                public void onSuccess(String response) {
                    Log.i("RESPONSE::", response);
                    /*try {
                        if (!response.equalsIgnoreCase("null") && response.length() > 0) {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                ObjectMapper objectMapper = new ObjectMapper();
                                objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                                employeeTrackingVO = objectMapper.readValue(jsonObject.toString(), EmployeeTrackingVO.class);
                                setAlarmActivity(activity,context);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }

                @Override
                public void onFailure(String errorMessage) {
                    Log.i("ERROR::", errorMessage);
                }
            });

        } catch (JSONException je) {
            je.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
