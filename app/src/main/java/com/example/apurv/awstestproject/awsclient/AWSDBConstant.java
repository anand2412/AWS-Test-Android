package com.example.apurv.awstestproject.awsclient;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Akhilesh Dhar Dubey on 3/6/15.<br/>
 * Email id: akhilesh.dubey@intelligrape.com<br/>
 * Skype id: akhileshdubey91
 * <p/>
 */

public interface AWSDBConstant {

    // Table  CREATE POST
    String TABLE_NAME_CREATE_POST = "loudshoutPosts";
    String AWS_REQUEST_DYNAMO_FAVOURITE_POST ="FAVOURITE_POST" ;
    // Column
    String COLUMN_CREATE_POST_CHANNEL_ID = "channelID";
    String COLUMN_CREATE_POST_POST_ID = "postID";
    String COLUMN_CREATE_POST_NUMBER_OF_COMMENTS = "numberOfComments";
    String COLUMN_CREATE_POST_UP_VOTES = "upvotes";
    String COLUMN_CREATE_POST_DOWN_VOTES = "downvotes";
    String COLUMN_CREATE_POST_FLAGS = "flags";
    String COLUMN_CREATE_POST_POST_TEXT = "postText";
    String COLUMN_CREATE_POST_POST_TIME = "postTime";
    String COLUMN_CREATE_POST_USER_POINTS = "userPoints";
    String COLUMN_CREATE_POST_COMPOSER_ID = "composerID";
    String COLUMN_CREATE_POST_VISIBLE = "flagStatus";
    String COLUMN_CREATE_POST_LAT = "lat";
    String COLUMN_CREATE_POST_LONG = "lng";
    String COLUMN_CREATE_POST_AVATAR = "avatar";
    String COLUMN_CREATE_POST_DELETE_STATUS = "deleteStatus";
    String COLUMN_CREATE_POST_SHARE_NO = "sharesNo";
    String COLUMN_CREATE_POST_FAVOURITES = "favourites";
    String COLUMN_CREATE_POST_CHANNEL_NAME = "channelName";

    // Table  BASECAMP
    String TABLE_NAME_CHANNELS = "loudshoutChannels";
    String COLUMN_CHANNELS_CHANNEL_ID = "channelID";
    String COLUMN_CHANNELS_CHANNEL_NAME = "channelName";
    String COLUMN_CHANNELS_CHANNEL_TYPE = "channelType";


    // DataSets
    String COGNITO_TABLE_NAME_BASECAMP = "basecamp";
    String COLUMN_BASECAMP_CHANNEL_ID = "channelID";
    String COLUMN_BASECAMP_CHANNEL_NAME = "channelName";
    String COLUMN_BASECAMP_CHANNEL_TYPE = "channelType";

    //Digit
    String COGNITO_USER_DATA_TABLE_NAME = "userData";

    // Cognito Keys

    String COGNITO_TOKEN_KEY = "token";
    String COGNITO_SESSION_KEY = "session";
    String COGNITO_PHONE_NUMBER_KEY = "phoneNumber";
    String COGNITO_SECRET_KEY = "secret";
    String COGNITO_LATITUDE_KEY = "lat";
    String COGNITO_LONGITUDE_KEY = "lng";

    // Table  Comment
    String TABLE_NAME_LOUDSHOUT_COMMENT = "loudshoutComments";
    // Column
    String COLUMN_COMMENT_POST_ID = "postID";
    String COLUMN_COMMENT_CHANNEL_ID = "channelID";
    String COLUMN_COMMENT_COMMENT_ID = "commentID";
    String COLUMN_COMMENT_COMPOSER_ID = "composerID";
    String COLUMN_COMMENT_UP_VOTES = "upvotes";
    String COLUMN_COMMENT_DOWN_VOTES = "downvotes";
    String COLUMN_COMMENT_FLAGS = "flags";
    String COLUMN_COMMENT_COMMENT_TEXT = "commentText";
    String COLUMN_COMMENT_COMMENT_TIME = "commentTime";
    String COLUMN_COMMENT_DELETE_STATUS = "deleteStatus";

    // Table:
    String COGNITO_TABLE_UPVOTE_DOWNVOTE_POSTES = "upvotedDownvotedPosts";
    String COGNITO_COLUMN_CHANNEL_ID = "channelID";
    String COGNITO_COLUMN_POST_ID = "postID";
    String COGNITO_COLUMN_STATUS = "status";

    String COGNITO_TABLE_FAVOURITE ="favourites" ;

    String COGNITO_TABLE_NAME_PEEK_BASECAMP = "peekBasecamps";
    String COGNITO_USER_POINT_TABLE_NAME = "userScore";

    String COGNITO_SCORE_KEY = "score";


    //Table loudshoutCommentStatus
    String TABLE_NAME_LOUDSHOUT_COMMENTS_STATUS="loudshoutCommentStatus";
    String COLUMN_COMMENT_STATUS_AVATAR="avatar";
    String COLUMN_COMMENT_STATUS_COMMENTS="comments";
    String COLUMN_COMMENT_STATUS_POSTID="postID";
    String COLUMN_COMMENT_STATUS_USERID="userID";
    String COLUMN_COMMENT_STATUS_ORGINAL_COMPOSER="originalComposer";


    String COGNITO_TABLE_FLAGGED_POSTS = "flaggedPosts" ;
}
