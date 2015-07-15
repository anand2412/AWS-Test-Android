package com.example.apurv.awstestproject.awsclient.listeners;


import android.content.Loader;


import com.example.apurv.awstestproject.awsclient.model.AWSModel;

/**
 * Created by Apurv on 14/07/15.
 */
public interface OnLoaderFinishedListener {

    void onLoaderFinished(Loader loader, AWSModel awsModel);

}
