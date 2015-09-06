package io.c0nnector.github.paradise.ui.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.ui.home.tabs.TabItem;
import io.c0nnector.github.paradise.ui.views.BaseRelativeLayout;

/**
 * Startup tab view
 */
public class MainView extends BaseRelativeLayout {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager pager;


    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(FragmentManager fragmentManager){

        setupPager(fragmentManager);

        setupTabs();
    }

    /*****************************************************
     * ---------------- * Viewpager * --------------------
     ****************************************************/

    public void setupPager(FragmentManager fragmentManager) {

        List<TabItem> tabs = new ArrayList<>();

        tabs.add(new TabItem("trending", StartupFilter.TRENDING));
        tabs.add(new TabItem("new", StartupFilter.NEW));
        tabs.add(new TabItem("hiring", StartupFilter.HIRING));

        PagerAdapter adapter = new PagerAdapter(fragmentManager, tabs);
        pager.setAdapter(adapter);
    }


    /*****************************************************
     * ---------------- * Tabs * --------------------
     *
     *
     *
     ****************************************************/

    private void setupTabs() {
        tabLayout.setupWithViewPager(pager);
    }
}
