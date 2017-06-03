package com.liangquan.studyhome.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.liangquan.studyhome.R;
import com.liangquan.studyhome.ui.fragment.ListHomeFragment;

public class ListHomeActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "Debug_ListHomeActivity";
    private ListHomeFragment mListHomeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListHomeFragment = new ListHomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_list_home,mListHomeFragment,null)
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
