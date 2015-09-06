package io.c0nnector.github.paradise.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.ui.home.tabs.TabFragment;
import io.c0nnector.github.paradise.ui.home.tabs.TabItem;
import io.c0nnector.github.paradise.ui.views.BaseViewPagerAdapter;

/**
 * Startups viewpager adapter
 */
public class PagerAdapter extends BaseViewPagerAdapter<TabItem>{

    public PagerAdapter(FragmentManager fm, List<TabItem> list) {
        super(fm, list);
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(getFilter(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getName(position);
    }


    private String getName(int position){
        return getListItem(position).getName();
    }

    private StartupFilter getFilter(int position){
        return getListItem(position).getFilter();
    }
}
