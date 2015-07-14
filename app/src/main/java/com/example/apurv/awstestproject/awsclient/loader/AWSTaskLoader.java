package com.example.apurv.awstestproject.awsclient.loader;


import android.content.AsyncTaskLoader;

import com.loudshout.android.awsclient.AWSConstants;
import com.loudshout.android.awsclient.AWSDBConstant;
import com.loudshout.android.awsclient.AWSUtils;
import com.loudshout.android.awsclient.ValueObject;
import com.loudshout.android.awsclient.model.AWSModel;
import com.loudshout.android.awsclient.model.AWSRequest;
import com.loudshout.android.awsclient.model.AWSResponse;
import com.loudshout.android.parser.Parser;
import com.loudshout.android.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AWSTaskLoader extends AsyncTaskLoader<AWSModel> {

    private static final String TAG = "AmazonServiceTaskLoader";
    private final AWSRequest mAWSRequest;

    public AWSTaskLoader(AWSRequest awsRequest) {
        super(LoaderHandler.getContext());
        mAWSRequest = awsRequest;

    }

    @Override
    protected void onStartLoading() {
        Logger.i("*****onStartLoading() LoaderId:" + getId());
        super.onStartLoading();

    }

    @Override
    public AWSModel loadInBackground() {

        AWSModel awsModel = null;
        if(mAWSRequest.getRequestType().equals(AWSDBConstant.AWS_REQUEST_DYNAMO_FAVOURITE_POST)){
            awsModel=AWSUtils.updateFavourite(mAWSRequest);
        }

        if (mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_GET)) {
            awsModel = AWSUtils.getData(mAWSRequest);

        }
        if (mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_GET_QUERY_DATA)) {
            awsModel = AWSUtils.getQueriedData(mAWSRequest);

        }
        else if (mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_POST)) {
            awsModel = AWSUtils.postData(mAWSRequest);
        }else if(mAWSRequest.getRequestType().equals(AWSConstants.MY_POST_DELETE)){
            awsModel=AWSUtils.deleteMyPost(mAWSRequest);
        }else if(mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_GET_BATCH_FAVORITE)){
            awsModel=AWSUtils.getMyFavoriteData(mAWSRequest);
        }else if(mAWSRequest.getRequestType().equals(AWSConstants.HTTP_POST_REQUEST)){

            awsModel=AWSUtils.sendHttpPost(mAWSRequest);
            AWSResponse awsResponse = (AWSResponse) awsModel;
            if(mAWSRequest.getValueObject()!=null) {
                ValueObject valueObject = mAWSRequest.getValueObject();
                if (parseResposnse(awsResponse.getResponseJSONString(), mAWSRequest.getParser(), valueObject, awsResponse) == -1) {
                    awsResponse.setError(true);
                }
            }
        }else if(mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_GET_ITEM)){
            awsModel=AWSUtils.getCommentStatus(mAWSRequest);
        }else if(mAWSRequest.getRequestType().equals(AWSConstants.AWS_REQUEST_DYNAMO_SAVE)){
            awsModel=AWSUtils.saveMyData(mAWSRequest);
        }
        return awsModel;
    }

    @Override
    protected void onStopLoading() {
        Logger.i("*****onStopLoading() LoaderId:" + getId());
        super.onStopLoading();

    }

    /**
     * Subclasses must implement this to take care of resetting their loader,
     * as per {@link #reset()}.  This is not called by clients directly,
     * but as a result of a call to {@link #reset()}.
     * This will always be called from the process's main thread.
     */
    @Override
    protected void onReset() {
        Logger.i("*****onReset() LoaderId:" + getId());
        super.onReset();

    }

    /**
     * Called if the task was anceled before it was completed.  Gives the class a chance
     * to properly dispose of the result.
     *
     * @param data
     */
    @Override
    public void onCanceled(AWSModel data) {
        Logger.i("*****onCanceled() LoaderId:" + getId());
        super.onCanceled(data);
    }

    /**
     * Sends the result of the load to the registered listener. Should only be called by subclasses.
     * <p/>
     * Must be called from the process's main thread.
     *
     * @param data the result of the load
     */



    @Override
    public void deliverResult(AWSModel data) {
        Logger.i("*****deliverResult() LoaderId:" + getId());
        super.deliverResult(data);

    }

    private int parseResposnse(String response, Parser parser, ValueObject model, AWSResponse awsResponse) {
        if (response != null)
        {
            try
            {
                model = parser.parse(new JSONObject(response));
                awsResponse.setValueObject(model);
                return 1;
            }
            catch (JSONException e1)
            {
                e1.printStackTrace();
                return -1;
            }
        }

        return -1;

    }


}
