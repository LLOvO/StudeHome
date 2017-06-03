package com.liangquan.studyhome.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.model.ItemData;

import java.util.List;

/**
 * Created by Liangquan on 2017/3/11.
 */

public class DefListAdapter extends BaseAdapter {
    private static final String DEBUG_TAG = "DefListAdapter";
    private List<ItemData> mDatas;
    private Context mContext;
    private int Type0Count = 0;
    private int Type1Count = 0;
    public static final int VIEW_TYPE_LOGO = 0;
    public static final int VIEW_TYPE_ICON = 1;

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(DEBUG_TAG, "Image clicked, id " + String.valueOf(v.getTag()) + "  message:" + mDatas.get((int) v.getTag()).getTime());
            Toast.makeText(mContext, mDatas.get((int) v.getTag()).getTime(), Toast.LENGTH_SHORT).show();
        }
    };

    public DefListAdapter(Context context, List<ItemData> list) {
        mContext = context;
        mDatas = list;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        return ((ItemData) getItem(position)).getType();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        switch (getItemViewType(position)) {
            case VIEW_TYPE_LOGO:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_def_item, null);
                    Log.i(DEBUG_TAG, "Logo count " + ++Type0Count);
                    TextView textView = (TextView) convertView.findViewById(R.id.textView);
                    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = imageView;
                    viewHolder.textView = textView;
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.imageView.setImageResource(mDatas.get(position).getImageId());
                viewHolder.imageView.setTag(position);
                viewHolder.imageView.setOnClickListener(myListener);

        /*
        下面的代码也可以设置listview内部的控件的监听器
         */
//        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(DEBUG_TAG,"Image clicked, id" + String.valueOf(v.getTag()) + "  message:"+mDatas.get((int)v.getTag()).getTime());
//                Toast.makeText(mContext,mDatas.get((int)v.getTag()).getTime(),Toast.LENGTH_SHORT).show();
//            }
//        });

                viewHolder.textView.setText(mDatas.get(position).getTime());
                break;
            case VIEW_TYPE_ICON:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_def_item_2, null);
                    Log.i(DEBUG_TAG, "Icon count " + ++Type1Count);
                    TextView textView = (TextView) convertView.findViewById(R.id.textView);
                    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = imageView;
                    viewHolder.textView = textView;
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.imageView.setImageResource(R.drawable.c23);
                viewHolder.textView.setText("这是日历项");
                viewHolder.imageView.setTag(position);
                viewHolder.imageView.setOnClickListener(myListener);
                break;
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
