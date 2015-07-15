package com.example.apurv.awstestproject.awsclient.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.apurv.awstestproject.R;


/**
 * Created by Apurv on 14/07/15.
 */
public class DialogHandler {


    private Context mContext;
    private Dialog mProgressDialog;

    public DialogHandler(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    public DialogParams showProgressDialog(DialogParams dialogParams) {
        mProgressDialog = new Dialog(mContext);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.progress_bar); mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(dialogParams.isCanceledOnTouchOutside());
        mProgressDialog.setOnCancelListener(dialogParams.getOnCancelListener());
        mProgressDialog.show();
        return dialogParams;
    }

    public void showDefaultProgressDialog() {
        showProgressDialog(new DialogParams());
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public Dialog getmProgressDialog() {
        return mProgressDialog;
    }
}
