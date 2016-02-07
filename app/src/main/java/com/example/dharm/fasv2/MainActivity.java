package com.example.dharm.fasv2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Setup the toolbar at the top of the main activity with the name FASv2 and a hamburger
        // menu with settings.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        // Set up an adapter to manage the fragments in the view pager object.
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(
                getSupportFragmentManager());

        Map<String, String[]> fragmentMap = new LinkedHashMap<>();
        fragmentMap.put("Popular", new String[]{"popularity.desc", null});
        fragmentMap.put("Recent", new String[]{"release_date.desc", "recent"});
        fragmentMap.put("Vintage", new String[]{"release_date.desc", "vintage"});
        fragmentMap.put("Favorites", new String[]{"popularity.desc", null});

        for (Map.Entry<String, String[]> entry : fragmentMap.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putStringArray("fragmentParameters", entry.getValue());
            ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
            viewPagerFragment.setArguments(bundle);
            adapter.addFragment(viewPagerFragment, entry.getKey());
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
