package s3xy.de.android_lsamples.ui.activity;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import s3xy.de.android_lsamples.R;
import s3xy.de.android_lsamples.adapter.MenuAdapter;
import s3xy.de.android_lsamples.ui.fragments.HorizontalRecyclerViewFragment;
import s3xy.de.android_lsamples.ui.fragments.RecyclerViewFragment;


public class MyActivity extends ActionBarActivity implements RecyclerViewFragment.OnFragmentInteractionListener, MenuAdapter.OnItemClickListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.content_frame)
    RelativeLayout mContentFrame;
    @InjectView(R.id.left_drawer)
    RecyclerView mLeftDrawer;
    @InjectView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private ArrayList<String> mMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {
        };

        mMenuItems = new ArrayList<String>();

        mMenuItems.add("CardView");
        mMenuItems.add("Horizontal List");


        // improve performance by indicating the list if fixed size.
        mLeftDrawer.setHasFixedSize(true);
        mLeftDrawer.setLayoutManager(new LinearLayoutManager(this));

        mLeftDrawer.setAdapter(new MenuAdapter(mMenuItems, this));

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Fragment f = getFragmentManager().findFragmentByTag(RecyclerViewFragment.TAG);
        if (f == null) {
            f = RecyclerViewFragment.newInstance();
        }

        getFragmentManager().beginTransaction().replace(R.id.content_frame, f, RecyclerViewFragment.TAG).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onClick(View view, int position) {
        Log.d("NoodleSoup", "Click at postion: " + position);

        Fragment f;
        String tag;

        switch (position) {
            case 1:
                f = getFragmentManager().findFragmentByTag(HorizontalRecyclerViewFragment.TAG);
                tag = HorizontalRecyclerViewFragment.TAG;
                if (f == null) {
                    f = HorizontalRecyclerViewFragment.newInstance();
                }
                break;
            default:
                f = getFragmentManager().findFragmentByTag(RecyclerViewFragment.TAG);
                tag = RecyclerViewFragment.TAG;
                if (f == null) {
                    f = RecyclerViewFragment.newInstance();
                }
                break;
        }

        getFragmentManager().beginTransaction().replace(R.id.content_frame, f, tag).commit();

        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
