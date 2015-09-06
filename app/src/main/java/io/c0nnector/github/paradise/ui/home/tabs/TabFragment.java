package io.c0nnector.github.paradise.ui.home.tabs;

import android.os.Bundle;

import com.f2prateek.dart.InjectExtra;

import org.parceler.Parcels;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.ui.BaseFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView with a GridView.
 * <p/>
 * interface.
 */
public class TabFragment extends BaseFragment {

    @Bind(R.id.tabView)
    TabView tabView;

    @InjectExtra
    StartupFilter filter;


    public static TabFragment newInstance(StartupFilter filter) {

        TabFragment tabFragment = new TabFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable("filter", Parcels.wrap(filter));

        tabFragment.setArguments(arguments);

        return tabFragment;
    }

    public TabFragment() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab;
    }


    @Override
    protected void afterViews() {
        tabView.bind(filter, getActivity());
    }
}
