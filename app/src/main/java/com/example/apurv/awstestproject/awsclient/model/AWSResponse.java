package com.example.apurv.awstestproject.awsclient.model;




import com.example.apurv.awstestproject.awsclient.ValueObject;

import java.util.ArrayList;


/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */

public class AWSResponse implements AWSModel {
    private boolean isError;
    private String responseJSONString = "";
    private String errorMsg = "";
    private int httpStatusCode;
    private ValueObject valueObject;
    private ArrayList<ValueObject> valueObjectArrayList;
    //The loader id of loader which downloaded this data.
    private int loaderId;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResponseJSONString() {
        return responseJSONString;
    }

    public void setResponseJSONString(String responseJSONString) {
        this.responseJSONString = responseJSONString;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public ValueObject getValueObject() {
        return valueObject;
    }

    public void setValueObject(ValueObject valueObject) {
        this.valueObject = valueObject;
    }

    /**
     * Returns the id of the loader which downloaded this data.
     *
     * @return
     */
    public int getLoaderId() {
        return loaderId;
    }

    /**
     * Sets the Id of the loader which downloaded this data. You don't have to set it yourself, the loader which downloaded this data will set it internally
     *
     * @param loaderId
     */

    public void setLoaderId(int loaderId) {
        this.loaderId = loaderId;
    }

    public ArrayList<ValueObject> getValueObjectArrayList() {
        return valueObjectArrayList;
    }

    public void setValueObjectArrayList(ArrayList<ValueObject> valueObjectArrayList) {
        this.valueObjectArrayList = valueObjectArrayList;
    }


}

