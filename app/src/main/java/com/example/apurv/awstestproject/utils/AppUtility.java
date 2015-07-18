package com.example.apurv.awstestproject.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.apurv.awstestproject.R;
import com.example.apurv.awstestproject.awsclient.loader.LoaderHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 1/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AppUtility {

    private static final int totalCount = 3;
    private static final long timeIntervalinMilli = 4000;
    public static  boolean CHECKED_BASECAMP ;
    private static String locationResult = "";
    private static int currentCount = 0;
    private static boolean flag = false;


    public static String getDeviceId(Context context) {
        String str = null;
        str = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return str;
    }
    public static void hideKeyPad(Context context, View v){

        InputMethodManager inputManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public static void disableButton(final View v){
        v.setClickable(false);
        v.setEnabled(false);
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                v.setClickable(true);
                v.setEnabled(true);
            }
        }, 1000);
    }



    public static int dpToPx(float dp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }


    public static void showToast(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static boolean isConnectingToInternet(Activity _context){
        if(_context!=null && (!_context.isFinishing())) {
            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }
        }
        return false;
    }

    public static void showInternetSettingDialog(final Activity context) {
        if(!context.isFinishing()) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            // .setIcon(android.R.attr.alertDialogIcon)
            alertDialog.setTitle(context.getResources().getString(R.string.no_internet_title));
            alertDialog.setMessage(context.getResources().getString(R.string.no_internet_msg));
            alertDialog.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                }
            });
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                    System.exit(1);
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }


    public static void showSettingDialog(final Activity context,String title,
                                                String msg,  final int id) {
        AlertDialog alertDialog= new AlertDialog.Builder(context)
                // .setIcon(android.R.attr.alertDialogIcon)
                .setMessage(msg)
                .setTitle(title)

                .setPositiveButton(R.string.setting,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                switch (id) {
                                    case AppConstants.NO_INTERNET_CONNECTION:
                                        context.startActivity(new Intent(
                                                Settings.ACTION_SETTINGS));

                                        break;
                                    case AppConstants.NO_GPS_ACCESS:

                                        context.startActivity(new Intent(
                                                Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                        break;

                                    default:
                                        break;
                                }

                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                context.onBackPressed();
                            }
                        }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static ProgressDialog progressBarDialog(Activity activity, String title, String message, boolean cancelOnTouchOutside) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(cancelOnTouchOutside);

        return progressDialog;
    }

    public static String getISO8601StringForDate() {
        Date date=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public static int generateMyNumber()
    {
        int aNumber = 0;
        aNumber = (int)((Math.random() * 9000000)+1000000);
        return aNumber;
    }

    public static String getCurrentDataTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnected = false;
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable()
                    && cm.getActiveNetworkInfo().isConnected()) {
                isNetworkConnected = true;
            }
        }
        return isNetworkConnected;
    }

    public static String strTimeDiff (String commentTime, Date nowTime)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date comTime = null;
        try {
            comTime = dateFormat.parse(commentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int timeDifference = (int) ( nowTime.getTime() - comTime.getTime() ) / 1000;
        String timeAgo = "";
        if (timeDifference < 60)
        {
            timeAgo = timeDifference + " sec" + ((timeDifference > 1) ? "s" : "") + " ago";
        }
        else
        {
            timeDifference /= 60;
            if (timeDifference < 60)
            {
                timeAgo = timeDifference + " min" + ((timeDifference > 1) ? "s" : "") + " ago";
            }
            else
            {
                timeDifference /= 60;
                if (timeDifference < 24)
                {
                    timeAgo = timeDifference + " hour" + ((timeDifference > 1) ? "s" : "") + " ago";
                }
                else
                {
                    timeDifference /= 24;
                    if (timeDifference < 7)
                    {
                        timeAgo = timeDifference + " day" + ((timeDifference > 1) ? "s" : "") + " ago";
                    }
                    else if (timeDifference < 30)
                    {
                        timeDifference /= 7;
                        timeAgo = timeDifference + " week" + ((timeDifference > 1) ? "s" : "") + " ago";
                    }
                    else
                    {
                        timeDifference /= 30;
                        timeAgo = timeDifference + " month" + ((timeDifference > 1) ? "s" : "") + " ago";
                    }
                }
            }
        }
        return timeAgo;
    }
    public static Date currentDateTime(){
        Date nowDate=null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            nowDate= dateFormat.parse(dateFormat.format(new Date()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowDate;
    }

    public static void loggerEnabled() {
        LoaderHandler.setLoggerEnabled(true);
    }



}
