package com.mobileappsco.training.surprise;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 2/28/2016.
 */
public class Helpers {

    public static void displayToastAndLog(Context mContext, int type, String message) {
        switch (type) {
            case Log.ERROR:
                Log.e("MYAPP", message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                break;
            case Log.INFO:
                Log.i("MYAPP", message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                break;
            case Log.VERBOSE:
                Log.v("MYAPP", message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d("MYAPP", message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
