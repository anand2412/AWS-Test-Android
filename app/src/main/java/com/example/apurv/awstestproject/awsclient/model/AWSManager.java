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
import com.loudshout.android.helper.DeveloperAuthenticationProvider;
import com.loudshout.android.utils.AppConstants;
import com.loudshout.android.utils.PreferencesUtility;

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
    private DeveloperAuthenticationProvider developerProvider;

    private AWSManager() {
    }

    private AWSManager(Context context, DeveloperAuthenticationProvider developerAuthenticationProvider) {
        this.mContext = context.getApplicationContext();
        developerProvider=developerAuthenticationProvider;
        initClients();
        syncClient();
        if (ddb == null) {
            initddb();
        }

    }

    public static AWSManager init(Context context, DeveloperAuthenticationProvider developerAuthenticationProvider) {
        if (sAmazonClientManagerInstance == null) {
            sAmazonClientManagerInstance = new AWSManager(context,developerAuthenticationProvider);
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
        ddb.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
    }

    public void initClients(){
        try {
            mCredentials = new CognitoCachingCredentialsProvider(
                    mContext,
                    developerProvider,
                    Regions.EU_WEST_1);
        }catch (AmazonServiceException e){
            e.printStackTrace();
        }

    }


    private void syncClient() {

        try {
            mSyncClient = new CognitoSyncManager(
                    mContext,
                    Regions.EU_WEST_1, // Region
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

    public String getPostId() {
        String identityId = getCredential().getIdentityId();
        String epochTime = String.valueOf(getCurrentUnixTimeStamp());
        String hMacInput = identityId + epochTime;
        return getHMac(hMacInput);
    }

    public String getComposerId() {
        String identityId = getCredential().getIdentityId();
        return identityId;
    }

    public int getUpVote() {
        //todo: change it from shared pref.
        return 0;
    }

    public int getDownVote() {
        //todo: change it from shared pref.
        return 0;
    }

    public int getUserPoint(Dataset dataset, String column) {
       try {
           String value = dataset.get(column);
           return Integer.parseInt(value);
       }catch (Exception e){
           return 0;
       }
    }

    public int getNumberOfComments() {
        //todo: change it from shared pref.
        return 0;
    }

    public int getDeleteStatus() {
        //todo: change it from shared pref.
        //note: cognito get true as 1 and false as 0, so we have to send it as true and false in string.
        return 0;
    }

    public String getPostTime() {
//        Get the current time form system in format "2 June 2015 13:44 +530 IST"
//        DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm z");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));//IST//Asia/Kolkata// UTC
        return simpleDateFormat.format(new Date()).toString();
    }

    public double getLat() {
        return PreferencesUtility.getSharedPrefDoubleData(mContext, AppConstants.PREF_LATITUDE);
    }

    public double getLong() {
        return PreferencesUtility.getSharedPrefDoubleData(mContext, AppConstants.PREF_LONGITUDE);
    }

    public int getFlags() {
        //todo: change it from shared pref.
        return 0;
    }

    public int getAvatar() {
        Random rand = new Random();
        int randomNum = rand.nextInt(181);
        return randomNum+1;
    }

    private String getCurrentAvatarColorCode() {
        //todo: change it from shared pref.
        return "ff0000";
    }

    private int getCurrentAvatarNumber() {
        //todo: change it from shared pref.
        return 0;
    }

    public int getVisible() {
        //todo: change it from shared pref.
        return 0;
    }


    public long getCurrentUnixTimeStamp() {
        Date date = new Date();
        long t = date.getTime();
        String time = new Timestamp(t).getTime() + "";
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }

    public String getHMac(String data) {
        try {
            byte[] bytesOfMessage = data.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] rawMD5 = md.digest(bytesOfMessage);
            return asHex(rawMD5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String asHex(byte[] buffer) {
        Formatter formatter = new Formatter();
        for (byte b : buffer) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public int getSharesNo() {
        return 0;
    }

    public int getFavourites() {
        return 0;
    }
}
