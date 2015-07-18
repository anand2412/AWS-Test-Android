package com.example.apurv.awstestproject;

import android.app.Application;

import com.example.apurv.awstestproject.awsclient.loader.LoaderHandler;
import com.example.apurv.awstestproject.awsclient.model.AWSManager;
import com.example.apurv.awstestproject.utils.AppUtility;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 16/7/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class TestApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        AWSManager.init(this);

        LoaderHandler.setContext(this);
        AppUtility.loggerEnabled();
    }
}
