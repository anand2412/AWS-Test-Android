package com.example.apurv.awstestproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apurv.awstestproject.R;
import com.example.apurv.awstestproject.awsclient.AWSConstants;
import com.example.apurv.awstestproject.awsclient.listeners.OnLoadCompleteListener;
import com.example.apurv.awstestproject.awsclient.loader.LoaderHandler;
import com.example.apurv.awstestproject.awsclient.model.AWSModel;
import com.example.apurv.awstestproject.awsclient.model.AWSRequest;
import com.example.apurv.awstestproject.awsclient.model.AWSResponse;
import com.example.apurv.awstestproject.dto.Test;
import com.example.apurv.awstestproject.utils.AppUtility;

public class UpdateActivity extends Activity {

    private EditText mEdtTxtName;
    private EditText mEdtTxtAddress;
    private Button mBtnUpdate;
    private int mCustomerID,mEnrollment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent=getIntent();
        mCustomerID=intent.getIntExtra("customerID",0);
        mEnrollment=intent.getIntExtra("Enrollment",0);

        mEdtTxtName=(EditText)findViewById(R.id.edtTxt_name);
        mEdtTxtAddress=(EditText)findViewById(R.id.edtTxt_address);

        mBtnUpdate=(Button)findViewById(R.id.btn_update);
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateItem();

            }
        });
    }

    private void updateItem() {

        Test test=new Test();
        test.setName(mEdtTxtName.getText().toString());
        test.setAddress(mEdtTxtAddress.getText().toString());
        test.setCustomerID(mCustomerID);
        test.setEnrollmentNo(mEnrollment);

        AWSRequest awsRequest = new AWSRequest();
        awsRequest.setShowProgressDialog(true);
        awsRequest.setRequestType(AWSConstants.AWS_REQUEST_DYNAMO_UPDATE);

        awsRequest.setValueObject(test);
        LoaderHandler loaderHandler = LoaderHandler.newInstance(this, awsRequest);
        loaderHandler.setOnLoadCompleteListener(new OnLoadCompleteListener() {
                                                    @Override
                                                    public void onLoadComplete(AWSModel awsModel) {

                                                        AWSResponse awsResponse = (AWSResponse) awsModel;
                                                        if (awsResponse.isError()) {
                                                            AppUtility.showToast(UpdateActivity.this, "Failed");
                                                        } else {
                                                            AppUtility.showToast(UpdateActivity.this,"success");
                                                        }
                                                    }
                                                }

        );
        loaderHandler.loadData();
    }


}
