package com.example.dharm.fasv2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentAdapter manages the different fragments corresponding to each of the pages in the view
 * pager object of the application.
 */
public class FragmentAdapter extends FragmentPagerAdapter{

    // The list of fragments that the adapter contains.
    private final List<Fragment> mFragments = new ArrayList<>();

    // The list of titles for each page in the view pager. Each page corresponds to one of the
    // fragments in the list.
    private final List<String> mFragmentTitles = new ArrayList<>();

    // Default constructor for the FragmentAdapter.
    public FragmentAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Adds a new fragment to the list of fragments for the adapter.
     *
     * @param fragment An android.support.v4.app fragment.
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
