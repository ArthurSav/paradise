package io.c0nnector.github.paradise.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f2prateek.dart.Dart;

import butterknife.ButterKnife;

/**
 * Base Fragment
 *
 * Functionality:
 * - Inject @extras
 *
 */
public abstract class BaseFragment extends Fragment{

    /**
     * Use it to set your fragment layout id
     * @return layout id
     */
    @LayoutRes
    protected int getLayout(){return 0;}

    /**
     * Initialized with onViewCreated()
     */
    protected void afterViews(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inject extras
        Dart.inject(this, getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayout() == 0) return super.onCreateView(inflater,container, savedInstanceState);

        //create fragment layout
        View view = inflater.inflate(getLayout(), container, false);

        //inject butterknife
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterViews();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public static <F>Fragment create(F fragment){
        return null;
    }

}
