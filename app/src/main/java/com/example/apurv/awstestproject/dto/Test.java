package com.example.apurv.awstestproject.dto;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.example.apurv.awstestproject.awsclient.ValueObject;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 16/7/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */

@DynamoDBTable(tableName = "TestDemo")
public class Test extends ValueObject {


    private int EnrollmentNo,customerID;
    private String Name,Address;

    @DynamoDBAttribute(attributeName = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @DynamoDBRangeKey(attributeName = "CustomerID")
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @DynamoDBHashKey(attributeName = "EnrollmentNo")
    public int getEnrollmentNo() {
        return EnrollmentNo;
    }

    public void setEnrollmentNo(int enrollmentNo) {
        EnrollmentNo = enrollmentNo;
    }
}
