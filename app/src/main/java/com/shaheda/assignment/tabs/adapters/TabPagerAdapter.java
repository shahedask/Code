package com.shaheda.assignment.tabs.adapters;

import com.shaheda.assignment.tabs.TabFragment;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private static final short TABS_PAGE_COUNT = 2;

    private List<TabFragment> pagerFragments;

    public TabPagerAdapter(FragmentManager fragmentManager, TabFragment... fragments) {
        super(fragmentManager);
        if (fragments != null && fragments.length > 0) {
            pagerFragments = Arrays.asList(fragments);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return pagerFragments != null && position < pagerFragments.size() ? pagerFragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return TABS_PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pagerFragments != null && position < pagerFragments.size() ?
                pagerFragments.get(position).getTitle() :
                "Unknown";
    }
}
