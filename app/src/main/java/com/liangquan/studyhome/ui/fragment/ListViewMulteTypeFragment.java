package com.liangquan.studyhome.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.adapter.DefListAdapter;
import com.liangquan.studyhome.ui.model.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewMulteTypeFragment extends Fragment {
    private static final String DEBUG_TAG = "Debug_LVMTFragment";
    private ListView listView;
    private List<ItemData> mDataslist;
    private DefListAdapter mAdapter;
    private Context mContext;

    public ListViewMulteTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_main,container,false);
        listView = (ListView) linearLayout.findViewById(R.id.list_view_def);
        mAdapter = new DefListAdapter(mContext, mDataslist);
        listView.setDivider(getResources().getDrawable(R.drawable.abc_list_pressed_holo_dark));
        listView.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.list_head_view,null));
        listView.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.list_head_view,null));
        listView.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.list_footer_view,null));
        listView.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.list_footer_view,null));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(DEBUG_TAG,"parent: " + parent.getClass().getSimpleName().toString() + "  view " +
                        view.getClass().getSimpleName().toString() + "  position " + position + "  id " + id );
                //在listView 的Item点击事件中也可以更新item内部的控件
                TextView textView = (TextView)view.findViewById(R.id.textView);
                if (textView != null){

                    textView.setText("我被点击了");
                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /*
                有三种滑动状态
                OnScrollerListener.SCROLL_STATE_FLING,SCROLL_STATE_IDEL,SCROLL_STATE_TOUCH_SCROLL;
                 */
                Log.i(DEBUG_TAG,"scrollState" + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(DEBUG_TAG, "firstVisibleItem " + firstVisibleItem + "  visibleItemCount" + visibleItemCount +"  totalItemCount" + totalItemCount);
            }
        });
        listView.setAdapter(mAdapter);
        return linearLayout;
    }

    private void initData() {
        mDataslist = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            ItemData itemData = new ItemData();
            itemData.setImageId(R.mipmap.ic_launcher);
//            itemData.setTime((String) DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date(System.currentTimeMillis())));
            itemData.setTime("这是第" + i + "个item");
            if (i%3==0){
                itemData.setType(DefListAdapter.VIEW_TYPE_ICON);
            }else {
                itemData.setType(DefListAdapter.VIEW_TYPE_LOGO);
            }
            mDataslist.add(itemData);
        }
    }

}
