package io.c0nnector.github.paradise.ui.views;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * An enhanced viewpager adapter to display a list of items
 * @param <Item>
 */
public abstract class BaseViewPagerAdapter<Item> extends FragmentStatePagerAdapter {

    List<Item> list;


    public BaseViewPagerAdapter(FragmentManager fm, List<Item> list) {
        super(fm);

        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    public Item getListItem(int position){
        return list.get(position);
    }

    /**
     * Remove item
     * @param position
     */
    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
