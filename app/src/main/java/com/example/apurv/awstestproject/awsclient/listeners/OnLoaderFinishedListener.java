package com.example.apurv.awstestproject.awsclient.listeners;


import android.content.Loader;

import com.loudshout.android.awsclient.model.AWSModel;

/**
 * Created by noor on 04/05/15.
 */
public interface OnLoaderFinishedListener {

    void onLoaderFinished(Loader loader, AWSModel awsModel);

}
