package com.liangquan.studyhome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.model.WidgetDesc;

import java.util.ArrayList;

/**
 * Created by Liangquan on 2017/3/24.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {
    private String[] mGroups;
    private ArrayList<WidgetDesc> mChildren;
    private Context mContext;
    private View.OnClickListener mListener;

    public ExpandAdapter(Context context, String[] name, ArrayList<WidgetDesc> desc, View.OnClickListener listener) {
        mContext = context;
        mGroups = name;
        mChildren = desc;
        mListener = listener;
    }

    @Override
    public int getGroupCount() {
        return mGroups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildren.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandlist_first, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.expand_textview);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.expand_imageView);
            viewHolder.button = (Button) convertView.findViewById(R.id.expand_button);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        if (isExpanded) {
            viewHolder.imageView.setImageResource(android.R.drawable.arrow_down_float);
        } else {
            viewHolder.imageView.setImageResource(android.R.drawable.arrow_up_float);
        }
        viewHolder.button.setOnClickListener(mListener);
        viewHolder.textView.setText(mChildren.get(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandlist_second, null);
//            viewHolder = new ViewHolder();
//            viewHolder.textView =
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
        Button button;
    }
}
