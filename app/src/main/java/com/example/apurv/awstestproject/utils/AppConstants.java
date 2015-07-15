package com.example.apurv.awstestproject.utils;

/**
 * Project: <b> Chuglee </b> <br/>
 * Created by Apurv Pandey on 28/5/15 <br/>
 * E-mail Id : apurvp@intelligrape.com <br/>
 */
public interface AppConstants {
    String ACTION_ATTACH_BASE_CAMP_FRAGMENT = "ACTION_ATTACH_BASE_CAMP_FRAGMENT";
    String ACTION_ATTACH_CATEGORY_FRAGMENT = "ACTION_ATTACH_CATEGORY_FRAGMENT";
    String ACTION_OPEN_HOME_ACTIVITY = "ACTION_OPEN_HOME_ACTIVITY";
    String ACTION_ATTACH_CREATE_POST_FRAGMENT = "ACTION_ATTACH_CREATE_POST_FRAGMENT";
    String ACTION_ATTACH_HOME_FRAGMENT = "ACTION_ATTACH_HOME_FRAGMENT";
    String ACTION_FINISH_ACTIVITY = "ACTION_FINISH_ACTIVITY";
    String ACTION_PEEK = "ACTION_PEEK";
    String ACTION_ATTACH_NOTIFICATION_FRAGMENT = "ACTION_ATTACH_NOTIFICATION_FRAGMENT";


    String FROM_BASE_CAMP_FRAGMENT = "FROM_BASE_CAMP_FRAGMENT";

    String RESULT_SUCCESS = "RESULT_SUCCESS";



    String RESULT = "result";
    String FROM = "from";
    String ACTION = "action";

    /* Preference constants */
    String PREF_LATITUDE = "lat";
    String PREF_LONGITUDE = "long";
    String PREF_LOGIN_SUCCESS = "status";
    String PREF_BASE_CAMP = "base_camp";



    String PREF_APP = "Chuglee Pref";
    String URL_TERMS = "http://loudshout.in/terms.html";
    String URL_PP = "http://loudshout.in/privacy.html";
    String URL_ABOUT_US = "http://loudshout.in/aboutus.html";

    String TITLE_TERMS = "Terms & Conditions";
    String TITLE_PP = "Privacy Policy";


    //credentials


    String IDENTITY_POOL_ID = "eu-west-1:b6a5607d-3c9a-4ce6-af94-ed20c6226d12";

    String DEVELOPER_PROVIDER_NAME="login.loudshout.com";




    String HMAC_MD5_ALGORITHM = "HmacMD5";

    String BACKSTACK ="backstack" ;
    String TAG ="tag" ;
    int NO_INTERNET_CONNECTION = 1;
    int NO_GPS_ACCESS =2 ;



    int REQUEST_CODE_FRAGMENT_CREATE_POST = 600;


    String PREF_USER_IDENTITY ="user_identity" ;

    String PREF_USER_TOKEN ="user_token" ;


    String PREF_KEY_CONTACT = "contact_number";
    String PREF_KEY_VERIFY_TIME ="time" ;
}
