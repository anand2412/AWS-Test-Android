package com.example.apurv.awstestproject.awsclient.model;

import android.content.Context;
import android.util.Log;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.apurv.awstestproject.utils.AppConstants;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.TimeZone;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AWSManager {

    private static final String LOG_TAG = "AmazonClientManager";
    private static AWSManager sAmazonClientManagerInstance;
    private AmazonDynamoDBClient ddb = null;
    private Context mContext;
    private CognitoCachingCredentialsProvider mCredentials;
    private CognitoSyncManager mSyncClient;


    private AWSManager() {
    }

    private AWSManager(Context context) {
        this.mContext = context.getApplicationContext();

        initClients();
        syncClient();
        if (ddb == null) {
            initddb();
        }

    }

    public static AWSManager init(Context context) {
        if (sAmazonClientManagerInstance == null) {
            sAmazonClientManagerInstance = new AWSManager(context);
        }
        return sAmazonClientManagerInstance;
    }

    public static AWSManager getInstance() {
        return sAmazonClientManagerInstance;
    }

    public CognitoCachingCredentialsProvider getCredential() {

        return mCredentials;
    }

    public AmazonDynamoDBClient ddb() {
        return ddb;
    }


    private void initddb() {

        ddb = new AmazonDynamoDBClient(mCredentials);
        ddb.setRegion(Region.getRegion(Regions.US_EAST_1));
    }

    public void initClients(){
        try {
            mCredentials = new CognitoCachingCredentialsProvider(
                    mContext,
                    AppConstants.IDENTITY_POOL_ID,
                    Regions.US_EAST_1);
        }catch (AmazonServiceException e){
            e.printStackTrace();
        }

    }


    private void syncClient() {

        try {
            mSyncClient = new CognitoSyncManager(
                    mContext,
                    Regions.US_EAST_1, // Region
                    mCredentials);
        }catch (AmazonServiceException e){
            e.printStackTrace();

        }
    }

    public CognitoSyncManager getSyncManagerObject() {
        return mSyncClient;
    }

    public boolean wipeCredentialsOnAuthError(AmazonServiceException ex) {

        Log.e(LOG_TAG, "Error, wipeCredentialsOnAuthError called" + ex);
        return ex.getErrorCode().equals("IncompleteSignature")
                || ex.getErrorCode().equals("InternalFailure")
                || ex.getErrorCode().equals("InvalidClientTokenId")
                || ex.getErrorCode().equals("OptInRequired")
                || ex.getErrorCode().equals("RequestExpired")
                || ex.getErrorCode().equals("ServiceUnavailable")

                // DynamoDB
                // http://docs.amazonwebservices.com/amazondynamodb/latest/developerguide/ErrorHandling.html#APIErrorTypes
                || ex.getErrorCode().equals("AccessDeniedException")
                || ex.getErrorCode().equals("IncompleteSignatureException")
                || ex.getErrorCode().equals(
                "MissingAuthenticationTokenException")
                || ex.getErrorCode().equals("ValidationException")
                || ex.getErrorCode().equals("InternalFailure")
                || ex.getErrorCode().equals("InternalServerError");

    }


    public Integer getBaseCampChannelId(Dataset dataset, String column) {
        try {
            String value = dataset.get(column);
            return Integer.parseInt(value);
        }catch (NumberFormatException e) {
            return -1;
        }
    }
    public String getBaseCampChannelName(Dataset dataset, String column) {
        String value = dataset.get(column);
        return value;
    }

    public String getComposerId() {
        String identityId = getCredential().getIdentityId();
        return identityId;
    }


}
