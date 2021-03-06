package com.example.apurv.awstestproject.awsclient.loader;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.loudshout.android.awsclient.AWSConstants;
import com.loudshout.android.awsclient.dialogs.DialogHandler;
import com.loudshout.android.awsclient.listeners.OnLoadCompleteListener;
import com.loudshout.android.awsclient.listeners.OnLoaderFinishedListener;
import com.loudshout.android.awsclient.model.AWSModel;
import com.loudshout.android.awsclient.model.AWSRequest;
import com.loudshout.android.awsclient.model.AWSResponse;
import com.loudshout.android.utils.AppUtility;

/**
 * Created by Tushar on 11-Jul-15.
 */
public class SplashLoader {

    private static final String TAG = "SplashLoader";
    // We will use this as loader id. Increase it by 1 every time you are going to create a new loader.
    private static int loaderId = -1;
    private static Context sContext;
    private static boolean loggerEnabled = false;
    private Fragment mFragment;
    private Activity mFragmentActivity;
    private AWSRequest mAWSRequest;
    private OnLoadCompleteListener mOnLoadCompleteListener = null;
    private LoaderManager mLoaderManager;
    private DialogHandler mDialogHandler;

    public SplashLoader(Activity fragmentActivity, AWSRequest awsRequest, LoaderManager loaderManager) {
        this.mFragmentActivity = fragmentActivity;
        this.mAWSRequest = awsRequest;
        this.mLoaderManager = loaderManager;

        LoaderManager.enableDebugLogging(loggerEnabled);
    }



    public static Context getContext() {
        if (sContext == null) {
            throw new NullPointerException(AWSConstants.APPLICATION_CONTEXT_NOT_SET_MESSAGE);
        }
        return sContext;
    }

    public static void setContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(AWSConstants.ILLEGAL_ARGUMENT_EXCEPTION_CONTEXT_NULL);
        }
        SplashLoader.sContext = context.getApplicationContext();
    }

    public static boolean isLoggerEnabled() {
        return loggerEnabled;
    }

    /**
     * Enable and disable the log information of the LoaderHandler.
     *
     * @param loggerEnabled If true, the logger will print the log info in logcat.
     */
    public static void setLoggerEnabled(boolean loggerEnabled) {
        SplashLoader.loggerEnabled = loggerEnabled;
    }

    /**
     * Will be called using activity reference
     *
     * @param activity
     * @param awsRequest
     * @return
     */
    public static SplashLoader newInstance(Activity activity, AWSRequest awsRequest) {
        return new SplashLoader(activity, awsRequest, activity.getLoaderManager());
    }



    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        this.mOnLoadCompleteListener = onLoadCompleteListener;
    }

    /**
     * Start loading data from server. Data will be received in registered {@link OnLoadCompleteListener} listener.
     */
    public void loadData() {
        if(!mFragmentActivity.isFinishing()) {
            if (AppUtility.isConnectingToInternet(mFragmentActivity)) {
                if (mAWSRequest.isShowProgressDialog()) {
                    if (mDialogHandler == null) {
                        mDialogHandler = new DialogHandler(mFragmentActivity);
                    }

                    mDialogHandler.showDefaultProgressDialog();
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable(AWSConstants.AWS_REQUEST, mAWSRequest);

                LoaderManager.LoaderCallbacks<AWSModel> myLoaderCallbacks = new AWSLoaderCallBacks<AWSModel>(new OnLoaderFinishedListener() {
                    @Override
                    public void onLoaderFinished(Loader loader, AWSModel data) {
                        if (mOnLoadCompleteListener != null) {
                            ((AWSResponse) data).setLoaderId(loader.getId());
                            mOnLoadCompleteListener.onLoadComplete(data);
                        }
                        //loader.reset();
                        mLoaderManager.destroyLoader(loader.getId());
                        if (mDialogHandler != null) {
                            mDialogHandler.dismissProgressDialog();
                        }

                    }
                });

                mLoaderManager.initLoader(++loaderId, bundle, myLoaderCallbacks).forceLoad();
            }
        }
    }
}
