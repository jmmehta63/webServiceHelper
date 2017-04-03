package com.jignesh.test.advertising.webservicehelper.notusable;

/**
 * Created by jignesh.mehta on 03-04-2017.
 */

import android.content.Context;


public class Global {

    // private DBConnect dbObj;
    private static Context context = null;
    private static volatile Global global = null;

    public static Global getInstance() {
        if (global == null) {
           /* synchronized (DBConnect.class) {
                global = new Global();
            }*/
        }
        return global;
    }


//    public static Global getInstance() {
//        if (global == null) {
//            global = new Global();
//        }
//        return global;
//    }

    public void init(Context context) {
        if (this.context == null) {
            this.context = context;
        }
    }

   /* public DBConnect getDbObj() {
        if (dbObj == null) {
            dbObj = new DBConnect(context, Constants.DB_NAME);
        }
        return dbObj;
    }*/


}
