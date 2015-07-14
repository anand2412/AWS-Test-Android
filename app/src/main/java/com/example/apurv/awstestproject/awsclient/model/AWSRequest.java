package com.example.apurv.awstestproject.awsclient.model;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.loudshout.android.awsclient.ValueObject;
import com.loudshout.android.parser.Parser;

import org.json.JSONObject;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class AWSRequest implements Parcelable {

    public static final Creator<AWSRequest> CREATOR = new Creator<AWSRequest>() {
        @Override
        public AWSRequest createFromParcel(Parcel parcel) {

            return new AWSRequest(parcel);
        }

        @Override
        public AWSRequest[] newArray(int size) {
            return new AWSRequest[size];
        }
    };

    private Bundle paramBundle;
    private String requestType;
    private String valueObjectFullyQualifiedName;
    private boolean showProgressDialog = true;
    private ValueObject valueObject;
    private ValueObject secondValueObject;
    private Parser<ValueObject> parser;
    private JSONObject jsonObject;
    private Activity activity;

    public AWSRequest() {
    }

    public AWSRequest(Parcel parcel) {

        paramBundle = parcel.readBundle();
        requestType = parcel.readString();
        showProgressDialog = parcel.readByte() == 1;
    }


    public Bundle getParamBundle() {
        return paramBundle;
    }

    public void setParamBundle(Bundle paramBundle) {
        this.paramBundle = paramBundle;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    public String getValueObjectFullyQualifiedName() {
        return valueObjectFullyQualifiedName;
    }

    public void setValueObjectFullyQualifiedName(String valueObjectFullyQualifiedName) {
        this.valueObjectFullyQualifiedName = valueObjectFullyQualifiedName;
    }

    public boolean isShowProgressDialog() {
        return showProgressDialog;
    }

    public void setShowProgressDialog(boolean showProgressDialog) {
        this.showProgressDialog = showProgressDialog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(paramBundle);
        dest.writeString(requestType);
        dest.writeByte((byte) (showProgressDialog ? 1 : 0));
    }

    public ValueObject getValueObject() {
        return valueObject;
    }

    public void setValueObject(ValueObject valueObject) {
        this.valueObject = valueObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Parser<ValueObject> getParser() {
        return parser;
    }

    public void setParser(Parser<ValueObject> parser) {
        this.parser = parser;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ValueObject getSecondValueObject() {
        return secondValueObject;
    }

    public void setSecondValueObject(ValueObject secondValueObject) {
        this.secondValueObject = secondValueObject;
    }
}
