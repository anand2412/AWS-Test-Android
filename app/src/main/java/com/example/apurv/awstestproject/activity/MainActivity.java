package com.example.apurv.awstestproject.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.apurv.awstestproject.R;
import com.example.apurv.awstestproject.adapter.TestDemoAdapter;
import com.example.apurv.awstestproject.awsclient.AWSConstants;
import com.example.apurv.awstestproject.awsclient.ValueObject;
import com.example.apurv.awstestproject.awsclient.listeners.OnLoadCompleteListener;
import com.example.apurv.awstestproject.awsclient.loader.LoaderHandler;
import com.example.apurv.awstestproject.awsclient.model.AWSModel;
import com.example.apurv.awstestproject.awsclient.model.AWSRequest;
import com.example.apurv.awstestproject.awsclient.model.AWSResponse;
import com.example.apurv.awstestproject.dto.Test;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView mListViewData;
    private ArrayList<ValueObject> mFeedPostList;
    private TestDemoAdapter mTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewData=(ListView)findViewById(R.id.list_data);
        createDataRequest();

        mListViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("customerID",((Test)mFeedPostList.get(i)).getCustomerID());
                intent.putExtra("Enrollment",((Test)mFeedPostList.get(i)).getEnrollmentNo());
                startActivity(intent);

            }
        });


    }

    private void createDataRequest() {

            AWSRequest awsRequest = new AWSRequest();
            awsRequest.setShowProgressDialog(true);
            awsRequest.setRequestType(AWSConstants.AWS_REQUEST_DYNAMO_GET_QUERY_DATA);
            Test testData= new Test();
            awsRequest.setValueObjectFullyQualifiedName(Test.class.getName());
            awsRequest.setValueObject(testData);

            LoaderHandler loaderHandler = LoaderHandler.newInstance(this, awsRequest);
            loaderHandler.setOnLoadCompleteListener(new OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(AWSModel awsModel) {

                    AWSResponse awsResponse = (AWSResponse) awsModel;
                    if (awsResponse.isError()) {

                    } else {
                        mFeedPostList=new ArrayList<ValueObject>();
                        for (int i=0; i<awsResponse.getValueObjectArrayList().size(); i++) {
                                mFeedPostList.add(awsResponse.getValueObjectArrayList().get(i));
                            }
                        }

                        setDataInList();

                    }


            });
            loaderHandler.loadData();



    }

    private void setDataInList() {

        if(mFeedPostList!=null) {
            mTestAdapter = new TestDemoAdapter(this, mFeedPostList);
            mListViewData.setAdapter(mTestAdapter);
        }


    }
}
