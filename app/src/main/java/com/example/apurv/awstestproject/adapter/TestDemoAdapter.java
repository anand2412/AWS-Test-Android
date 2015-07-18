package com.example.apurv.awstestproject.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apurv.awstestproject.R;
import com.example.apurv.awstestproject.activity.MainActivity;
import com.example.apurv.awstestproject.awsclient.ValueObject;
import com.example.apurv.awstestproject.dto.Test;

import java.util.ArrayList;

/**
 * <p/>
 * Project: <b>Chuglee</b><br/>
 * Created by: Anand K. Rai on 16/7/15.<br/>
 * Email id: anand.rai@tothenew.com<br/>
 * <p/>
 */
public class TestDemoAdapter extends BaseAdapter {

    private ArrayList<ValueObject> feedPostList;
    private Activity activity;

    public TestDemoAdapter(Activity activity, ArrayList<ValueObject> mFeedPostList) {
        this.activity=activity;
        this.feedPostList=mFeedPostList;
    }

    @Override
    public int getCount() {
        return feedPostList.size();
    }

    @Override
    public ValueObject getItem(int i) {
        return feedPostList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=activity.getLayoutInflater().inflate(R.layout.item_row,null);
        TextView txtName=(TextView)v.findViewById(R.id.textview_name);
        TextView txtAddress=(TextView)v.findViewById(R.id.textview_address);

        txtName.setText(((Test)feedPostList.get(i)).getName());
        txtAddress.setText(((Test)feedPostList.get(i)).getAddress());

        return v;
    }
}
