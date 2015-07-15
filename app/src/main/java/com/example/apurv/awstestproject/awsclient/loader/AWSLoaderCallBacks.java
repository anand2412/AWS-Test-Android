package com.example.apurv.awstestproject.awsclient.loader;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

import com.example.apurv.awstestproject.awsclient.AWSConstants;
import com.example.apurv.awstestproject.awsclient.listeners.OnLoaderFinishedListener;
import com.example.apurv.awstestproject.awsclient.model.AWSModel;
import com.example.apurv.awstestproject.awsclient.model.AWSRequest;

import java.util.logging.Logger;


/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AWSLoaderCallBacks<M> implements LoaderManager.LoaderCallbacks<AWSModel> {

    private static final String TAG = "AWSLoaderCallbacks";
    private OnLoaderFinishedListener mOnLoaderFinishedListener;


    public AWSLoaderCallBacks(OnLoaderFinishedListener mOnLoaderFinishedListener) {
        //mOnLoadCompleteListener = onLoadCompleteListener;
        this.mOnLoaderFinishedListener = mOnLoaderFinishedListener;
    }

    @Override
    public Loader<AWSModel> onCreateLoader(int id, Bundle bundle) {
        AWSRequest awsRequest = bundle.getParcelable(AWSConstants.AWS_REQUEST);
        Log.i(".....","*****onCreateLoader() LoaderId:" + id + " Bundle:" + bundle.toString() + " requestParamBundle:" + awsRequest.getParamBundle());

        AWSTaskLoader amazonServiceTaskLoader = new AWSTaskLoader(awsRequest);
        return amazonServiceTaskLoader;
    }

    @Override
    public void onLoadFinished(Loader<AWSModel> loader, AWSModel data) {
        Log.i("","*****onLoadFinished() LoaderId:" + loader.getId());
        mOnLoaderFinishedListener.onLoaderFinished(loader, data);

    }

    @Override
    public void onLoaderReset(Loader<AWSModel> loader) {
        Log.i("", "*****onLoaderReset() LoaderId:" + loader.getId());

    }
}
