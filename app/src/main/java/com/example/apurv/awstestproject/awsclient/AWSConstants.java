package com.example.apurv.awstestproject.awsclient;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 3/6/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public interface AWSConstants {

    String AWS_REQUEST_DYNAMO_GET = "DYNAMO_GET";
    String AWS_REQUEST_DYNAMO_POST = "DYNAMO_POST";
    String AWS_REQUEST_TYPE_POST = "POST";
    String AWS_REQUEST = "AWSRequest";
    String APPLICATION_CONTEXT_NOT_SET_MESSAGE = "The Context is not set. Please call \"LoaderHandler.setContext(Context)\" from your Application subclass.";
    String ILLEGAL_ARGUMENT_EXCEPTION_CONTEXT_NULL = "The Context object is null. It should be a non null valid value.";


    String AWS_REQUEST_DYNAMO_GET_QUERY_DATA ="DYNAMO_QUERY_GET" ;
    String MY_POST_DELETE ="delete" ;

    String AWS_REQUEST_DYNAMO_GET_BATCH_FAVORITE ="my_favorite" ;
    String HTTP_POST_REQUEST = "http_post";
    String AWS_REQUEST_DYNAMO_GET_ITEM = "get_item";

    // user point action

    String UPVOTE_POST_ACTION = "upvote-post";
    String DOWNVOTE_POST_ACTION = "downvote-post";
    String COMPOSE_POST_ACTION= "compose-post";
    String COMPOSE_COMMENT_ACTION = "compose-comment";
    String DELETE_POST_ACTION = "delete-post";
    String UPVOTE_COMMENT_ACTION = "upvote-comment";
    String DOWNVOTE_COMMENT_ACTION = "downvote-comment";
    String SHARE_POST = "share-post";
    String SET_BASECAMP_ACTION = "set-basecamp";
    String PEEK_BASECAMP_ACTION = "peek-basecamp";
    String APP_SHARE_ACTION = "app-share";
    String FAVORITE_ACTION="favourite-post";

    String AWS_REQUEST_DYNAMO_SAVE = "table_save";
}
