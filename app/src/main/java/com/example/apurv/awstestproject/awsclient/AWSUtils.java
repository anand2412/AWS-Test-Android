package com.example.apurv.awstestproject.awsclient;


import android.os.Bundle;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.example.apurv.awstestproject.awsclient.model.AWSManager;
import com.example.apurv.awstestproject.awsclient.model.AWSModel;
import com.example.apurv.awstestproject.awsclient.model.AWSRequest;
import com.example.apurv.awstestproject.awsclient.model.AWSResponse;
import com.example.apurv.awstestproject.dto.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AWSUtils {
    private static final int CONNECTION_TIMEOUT = 25000;
    private static final int SOCKET_TIMEOUT = 60000;

    public static AWSResponse getData(AWSRequest awsRequest) {

        Class<? extends ValueObject> valueObjectClass = null;
        try {
            valueObjectClass = (Class<? extends ValueObject>) Class.forName(awsRequest.getValueObjectFullyQualifiedName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DynamoDBMapper mapper = new DynamoDBMapper(AWSManager.getInstance().ddb());

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        try {
            PaginatedScanList<ValueObject> result = (PaginatedScanList) mapper.scan(valueObjectClass, scanExpression);


            AWSResponse awsResponse = new AWSResponse();
            ArrayList<ValueObject> resultList = new ArrayList<>();
            for (ValueObject up : result) {
                resultList.add(up);
            }
            awsResponse.setValueObjectArrayList(resultList);
            awsResponse.setError(false);

            return awsResponse;

        } catch (AmazonServiceException ex) {
            AWSManager.getInstance()
                    .wipeCredentialsOnAuthError(ex);
            AWSResponse awsResponse = new AWSResponse();
            awsResponse.setErrorMsg(""+ex.toString());
            awsResponse.setError(true);
            return awsResponse;
        }
        catch (Exception e){
            e.printStackTrace();
            AWSResponse awsResponse = new AWSResponse();
            awsResponse.setErrorMsg(""+e.toString());

            awsResponse.setError(true);
            return awsResponse;
        }
    }


    public static AWSResponse updateItem(AWSRequest awsRequest) {
        ValueObject valueObject = awsRequest.getValueObject();
        Test test = (Test) valueObject;

        AWSResponse awsResponse = new AWSResponse();
        try {
            AttributeValue enrollment = new AttributeValue()
                    .withN(String.valueOf(test.getEnrollmentNo()));
            AttributeValue customerID = new AttributeValue()
                    .withN(String.valueOf(test.getCustomerID()));

            Map<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
            updateItems.put("Name", new AttributeValueUpdate().withAction(AttributeAction.PUT).withValue(new AttributeValue().withS(test.getName())));
            updateItems.put("Address", new AttributeValueUpdate().withAction(AttributeAction.PUT).withValue(new AttributeValue().withS(test.getAddress())));

            Map.Entry<String, AttributeValue> hashKey = new AbstractMap.SimpleEntry<String, AttributeValue>("EnrollmentNo", enrollment);
            Map.Entry<String, AttributeValue> rangeKey = new AbstractMap.SimpleEntry<String, AttributeValue>("CustomerID", customerID);

            final UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                    .withTableName("TestDemo")
                    .withKey(hashKey, rangeKey)
                    .withAttributeUpdates(updateItems);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    UpdateItemResult result = AWSManager.getInstance().ddb().updateItem(updateItemRequest);

                }
            }).start();

        } catch (ConditionalCheckFailedException e) {
            // The conditional check failed.
            e.printStackTrace();
            awsResponse.setError(true);

        } catch (AmazonServiceException e) {
            AWSManager.getInstance()
                    .wipeCredentialsOnAuthError(e);
            awsResponse.setErrorMsg(""+e.toString());
            awsResponse.setError(true);
        }
        catch (Exception e){
            e.printStackTrace();
            awsResponse.setError(true);
            awsResponse.setErrorMsg("" + e.toString());
            return awsResponse;
        }
        return awsResponse;
    }

    public static AWSResponse postData(AWSRequest awsRequest) {

        AWSResponse awsResponse = new AWSResponse();
        try {
            ValueObject valueObject = awsRequest.getValueObject();
            DynamoDBMapper mapper = new DynamoDBMapper(AWSManager.getInstance().ddb());
            mapper.save(valueObject);
            awsResponse.setError(false);


        } catch (AmazonServiceException e) {
            AWSManager.getInstance()
                    .wipeCredentialsOnAuthError(e);
            awsResponse.setError(true);
            awsResponse.setErrorMsg(""+e.toString());
        } catch (Exception e){
            e.printStackTrace();
            awsResponse.setErrorMsg(""+e.toString());
            awsResponse.setError(true);
            return awsResponse;
        }
        return awsResponse;
    }

    /*public static AWSResponse updateUpVoteDownVotePostData(AWSRequest awsRequest) {

        String secondColumn=null;
        ValueObject valueObject = awsRequest.getValueObject();


        AWSResponse awsResponse = new AWSResponse();
        try {
            AttributeValue channelID = new AttributeValue()
                    .withN(String.valueOf(createPosts.getChannelID()));
            AttributeValue postID = new AttributeValue()
                    .withS(String.valueOf(createPosts.getPostID()));

            Map<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
            Bundle bundle = awsRequest.getParamBundle();
            String columnName = bundle.getString("columnName", "null");
            boolean isUpDown=bundle.getBoolean("vote");
                if(isUpDown){
                     secondColumn = bundle.getString("secondColumnName");
                }

            if (columnName.equals("null")) {
                awsResponse.setError(true);
                return awsResponse;
            }
            if(secondColumn!=null)
                updateItems.put(secondColumn, new AttributeValueUpdate().withAction(AttributeAction.ADD).withValue(new AttributeValue().withN("-1")));
            updateItems.put(columnName, new AttributeValueUpdate().withAction(AttributeAction.ADD).withValue(new AttributeValue().withN("1")));

            Map.Entry<String, AttributeValue> hashKey = new AbstractMap.SimpleEntry<String, AttributeValue>("channelID", channelID);
            Map.Entry<String, AttributeValue> rangeKey = new AbstractMap.SimpleEntry<String, AttributeValue>("postID", postID);

            final UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                    .withTableName(AWSDBConstant.TABLE_NAME_CREATE_POST)
                    .withKey(hashKey, rangeKey)
                    .withAttributeUpdates(updateItems);

            if(columnName.equalsIgnoreCase("upvotes")){
                AWSUtils.invokeLambdaForUserPoints(awsRequest.getActivity(),AWSConstants.UPVOTE_POST_ACTION ,createPosts.getPostID(), "" + createPosts.getChannelID(),"",""+createPosts.getUpvotes(),""+createPosts.getDownvotes());
            }else if(columnName.equalsIgnoreCase("downvotes")){
                AWSUtils.invokeLambdaForUserPoints(awsRequest.getActivity(),AWSConstants.DOWNVOTE_POST_ACTION ,createPosts.getPostID(), "" + createPosts.getChannelID(),"",""+createPosts.getUpvotes(),""+createPosts.getDownvotes());
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    UpdateItemResult result = AWSManager.getInstance().ddb().updateItem(updateItemRequest);
                    Logger.e("Success!!!!");
                }
            }).start();

        } catch (ConditionalCheckFailedException e) {
            // The conditional check failed.
            e.printStackTrace();
            awsResponse.setErrorMsg(""+e.toString());
            awsResponse.setError(true);

        } catch (AmazonServiceException e) {
            AWSManager.getInstance()
                    .wipeCredentialsOnAuthError(e);
            awsResponse.setErrorMsg(""+e.toString());
            awsResponse.setError(true);
        }
        catch (Exception e){
            e.printStackTrace();
            awsResponse.setError(true);
            awsResponse.setErrorMsg(""+e.toString());
            return awsResponse;
        }
        return awsResponse;
    }*/




    public static AWSModel getQueriedData(AWSRequest awsRequest) {

/*        Bundle bundle=  awsRequest.getParamBundle();
        String indexName=bundle.getString("IndexName");*/
        ValueObject valueObject=awsRequest.getValueObject();
        Class<? extends ValueObject> valueObjectClass = null;
        try {
            valueObjectClass = (Class<? extends ValueObject>) Class.forName(awsRequest.getValueObjectFullyQualifiedName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        DynamoDBMapper mapper = new DynamoDBMapper(AWSManager.getInstance().ddb());
        DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                .withHashKeyValues(valueObject)
                .withScanIndexForward(false)
                .withConsistentRead(false);

        try {
           // PaginatedQueryList resPaginatedQueryList=new PaginatedQueryList(mapper,valueObjectClass,queryResult,queryRequest, DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING,new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.CLOBBER));
            PaginatedQueryList<ValueObject> result = (PaginatedQueryList) mapper.query(valueObjectClass, queryExpression);

            AWSResponse awsResponse = new AWSResponse();
            ArrayList<ValueObject> resultList = new ArrayList<>();
            for (ValueObject up : result) {
                resultList.add(up);
            }
            awsResponse.setValueObjectArrayList(resultList);
            awsResponse.setError(false);

            return awsResponse;

        } catch (AmazonServiceException ex) {
            AWSManager.getInstance()
                    .wipeCredentialsOnAuthError(ex);
            AWSResponse awsResponse = new AWSResponse();
            awsResponse.setErrorMsg(""+ex.toString());
            awsResponse.setError(true);
            return awsResponse;
        }
        catch (Exception e){
            e.printStackTrace();
            AWSResponse awsResponse = new AWSResponse();
            awsResponse.setErrorMsg(""+e.toString());
            awsResponse.setError(true);
            return awsResponse;
        }

    }




   /* public static void invokeLambdaForUserPoints(Activity activity, final String action, final String postId, final String channelId, final String commentId, final String upVotes, final String downVotes) {

        LambdaInvokerFactory factory = new LambdaInvokerFactory(activity,
                Regions.AP_NORTHEAST_1, AWSManager.getInstance().getCredential());

        final UserPointInterface myInterface = factory.build(UserPointInterface.class);

        //  NameInfo nameInfo = new NameInfo("John", "Doe");
        // The Lambda function invocation results in a network call.
        // Make sure it is not called from the main thread.
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {

                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.

                try {
                    HashMap<String, String> hashMap=new HashMap<>();
                    hashMap.put("activity", action);
                    hashMap.put("postID", postId);
                    hashMap.put("channelID",channelId);
                    hashMap.put("commentID",commentId);
                    hashMap.put("upvotes",upVotes);
                    hashMap.put("downvotes", downVotes);
                    return myInterface.activity(hashMap);
                } catch (LambdaFunctionException lfe) {
                    Log.e("Tag", "Failed to invoke echo", lfe);
                    return null;
                }catch (InvalidIdentityPoolConfigurationException ex){
                    ex.printStackTrace();
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null) {
                    return;
                }
                else{
                    Logger.i(result);
                }
            }
        }.execute();
    }
*/

}

