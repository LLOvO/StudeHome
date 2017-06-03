package com.liangquan.studyhome.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.adapter.ExpandAdapter;
import com.liangquan.studyhome.ui.model.WidgetDesc;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListHomeFragment extends Fragment implements View.OnClickListener {
    private String[] mFragments = {"ListViewMulteTypeFragment"};
    private ArrayList<WidgetDesc> mDescs;


    private ExpandableListView expandableListView;

    public ListHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDescs = new ArrayList<>();
        mDescs.add(new WidgetDesc(mFragments[0]));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableListView = (ExpandableListView) view.findViewById(R.id.frag_ui_list_home);
        expandableListView.setAdapter(new ExpandAdapter(getActivity(), mFragments, mDescs, this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expand_button:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_list_home, new ListViewMulteTypeFragment()).
                        addToBackStack(null)
                        .commit();
        }
    }
}
