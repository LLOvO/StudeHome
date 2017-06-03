package com.liangquan.studyhome.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.activity.ListHomeActivity;
import com.liangquan.studyhome.ui.activity.MaterialDesignActivity;


public class UIFragment extends Fragment implements View.OnClickListener {
    public static final int SHOW_TAG = 1001;

    public UIFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GridLayout view = (GridLayout) inflater.inflate(R.layout.fragment_ui, container, false);
        if (view.getChildCount() != 0) {
            for (int i =0; i< view.getChildCount(); i++ ){
                view.getChildAt(i).setOnClickListener(this);
            }

        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag_ui_btn_listview:
                startActivity(new Intent(getActivity(), ListHomeActivity.class));
                break;
            case R.id.frag_ui_btn_lollipop:
                startActivity(new Intent(getActivity(), MaterialDesignActivity.class));
                break;
        }
    }

}
