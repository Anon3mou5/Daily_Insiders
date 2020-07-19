package com.example.newsapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"business-insider","CNN","BUZZFEED","ESPN","bbc-news","politico","bloomberg","google-news","AL-JAZEERA-ENGLISH","news-com-au"};
    private final Context mContext;
    int total;

    public SectionsPagerAdapter(Context context, FragmentManager fm,int total) {
        super(fm);
        mContext = context;
        this.total=total;
    }

    @Override
    public Fragment getItem(int position) {
        return new PlaceholderFragment(TAB_TITLES[position]);
        }


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString();
//    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return total;
    }
}