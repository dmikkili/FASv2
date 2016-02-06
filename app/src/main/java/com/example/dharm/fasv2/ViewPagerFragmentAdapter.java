package com.example.dharm.fasv2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPagerFragmentAdapter manages the different fragments corresponding to each of the pages in
 * the ViewPager object of the application.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter{

    // The list of fragments that the adapter contains.
    private final List<Fragment> mFragments = new ArrayList<>();

    // The list of titles for each page in the ViewPager object. Each page corresponds to one of the
    // fragments in the list.
    private final List<String> mFragmentTitles = new ArrayList<>();

    // Default constructor for the ViewPagerFragmentAdapter.
    public ViewPagerFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Adds a new fragment to the list of fragments for the adapter.
     *
     * @param fragment A ViewPagerFragment object.
     * @param title    The fragment name to use for the title of the page the fragment is associated
     *                 with.
     */
    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

}
