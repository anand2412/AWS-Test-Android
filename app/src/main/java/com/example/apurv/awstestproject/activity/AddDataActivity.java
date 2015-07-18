package com.example.apurv.awstestproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
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

public class AddDataActivity extends Activity {

    private EditText mEdtTxtName,mEdtTxtAddress,mEdtTxtId,mEdtTxtEnNo;
    private Button mBtnSave;
    private Button mBtnGetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mEdtTxtName=(EditText)findViewById(R.id.edt_txt_name);
        mEdtTxtAddress=(EditText)findViewById(R.id.edt_txt_address);
        mEdtTxtId=(EditText)findViewById(R.id.edt_txt_customerID);
        mEdtTxtEnNo=(EditText)findViewById(R.id.edt_txt_en_no);

        mBtnSave=(Button)findViewById(R.id.btn_save);
        mBtnGetList=(Button)findViewById(R.id.btn_next);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveDataInTable();


            }
        });
        mBtnGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddDataActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void saveDataInTable() {

        Test test=new Test();
        test.setName(mEdtTxtName.getText().toString());
        test.setAddress(mEdtTxtAddress.getText().toString());
        test.setCustomerID(Integer.parseInt(mEdtTxtId.getText().toString()));
        test.setEnrollmentNo(Integer.parseInt(mEdtTxtEnNo.getText().toString()));

        AWSRequest awsRequest = new AWSRequest();
        awsRequest.setShowProgressDialog(true);
        awsRequest.setRequestType(AWSConstants.AWS_REQUEST_DYNAMO_POST);

        awsRequest.setValueObject(test);
        LoaderHandler loaderHandler = LoaderHandler.newInstance(this, awsRequest);
        loaderHandler.setOnLoadCompleteListener(new OnLoadCompleteListener() {
                                                    @Override
                                                    public void onLoadComplete(AWSModel awsModel) {

                                                        AWSResponse awsResponse = (AWSResponse) awsModel;
                                                        if (awsResponse.isError()) {
                                                            AppUtility.showToast(AddDataActivity.this,"Failed");
                                                        } else {
                                                            AppUtility.showToast(AddDataActivity.this,"success");
                                                        }
                                                    }
                                                }

        );
        loaderHandler.loadData();

    }
}
