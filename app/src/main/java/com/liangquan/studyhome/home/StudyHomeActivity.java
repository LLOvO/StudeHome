package com.liangquan.studyhome.home;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.liangquan.studyhome.home.fragment.StudyHomeFragment;
import com.liangquan.studyhome.home.fragment.UIFragment;
import com.liangquan.studyhome.R;
import com.liangquan.studyhome.home.fragment.WorkerFragment;

public class StudyHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String DEBUG_TAG = "Debug_StudyHomeAvtivity";
    private FragmentManager fragmentManager;
    private int ShowingFragment = 0;

    /*
    这里本来是用的Map<Integer, Fragment>的，IDE提示用SparseArray<Fragment>更合适。
     */
    private SparseArrayCompat<Fragment> sparseArrayCompat;
    private static final int FRAGMENT_CAP = 50;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_home);
        initView();
        initFragment();
        if (savedInstanceState == null) {
            replaceFragment(StudyHomeFragment.SHOW_TAG);
        }
    }


    private void initFragment() {

        sparseArrayCompat = new SparseArrayCompat<>(FRAGMENT_CAP);
        sparseArrayCompat.append(UIFragment.SHOW_TAG, new UIFragment());
        sparseArrayCompat.append(WorkerFragment.SHOW_TAG, new WorkerFragment());
        sparseArrayCompat.append(StudyHomeFragment.SHOW_TAG,new StudyHomeFragment());
        fragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //TODO 这里想要实现异常退出的时候在哪个Fragement，重新开启App就在哪个界面。

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("SHOW_TAG",ShowingFragment);
//        Log.d(DEBUG_TAG,"onSaveInstanceState" + "ShowingFragment" + ShowingFragment);
//        super.onSaveInstanceState(outState);
//    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        replaceFragment(savedInstanceState.getInt("SHOW_TAG",StudyHomeFragment.SHOW_TAG));
//        Log.d(DEBUG_TAG,"onRestoreInstanceState" + savedInstanceState.getInt("SHOW_TAG"));
//    }

    @Override
    public void onBackPressed() {

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.study_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ui) {
            replaceFragment(UIFragment.SHOW_TAG);
        } else if (id == R.id.nav_worker) {
            replaceFragment(WorkerFragment.SHOW_TAG);
        } else if (id == R.id.nav_file) {

        } else if (id == R.id.nav_utils) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(int showTag) {
        if (ShowingFragment != showTag) {
            fragmentManager.beginTransaction().replace(R.id.content_study_home, sparseArrayCompat.get(showTag)).commit();
            ShowingFragment = showTag;
        }
    }


    @Override
    public void onClick(View v) {

    }
}
