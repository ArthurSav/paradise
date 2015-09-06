package io.c0nnector.github.paradise.ui.home.tabs;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import au.com.gridstone.grex.GRexAndroidPersister;
import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.ApiService;
import io.c0nnector.github.paradise.api.RetroSubscriber;
import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.binders.StartupBinder;
import io.c0nnector.github.paradise.cache.StartupCache;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartup;
import io.c0nnector.github.paradise.ui.startup.ActivityStartupDetails;
import io.c0nnector.github.paradise.ui.views.BaseRelativeLayout;
import io.c0nnector.github.paradise.util.UtilView;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;
import io.c0nnector.github.least.ListItemListener;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Tab view that displays a list of startups
 */
public class TabView extends BaseRelativeLayout implements ListItemListener<Holdr_ListItemStartup, Startup> {

    /**
     * Helper class to cache responses
     */
    StartupCache startupCache;

    Activity activity;


    @Inject
    ApiService apiService;

    @Inject
    GRexAndroidPersister persist;


    @Bind(R.id.recyclerview)
    LeastView recyclerView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;


    LeastAdapter adapter;


    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public TabView(Context context, AttributeSet attrs) {

        super(context, attrs);

        if (!isInEditMode()) {

            //dagger
            Application.get(getContext()).inject(this);
        }
    }


    /**
     * If we have cached data, we show it, until we get a fresh new response.
     *
     * @param filter startup category
     */
    public void bind(StartupFilter filter) {

        this.startupCache = new StartupCache(persist, filter);

        Observable.concat(startupCache.get(), getStartups(filter))

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new RetroSubscriber<List<Startup>>() {

                    @Override
                    public void onNext(List<Startup> startups) {

                        super.onNext(startups);

                        setupRecyclerView(startups);
                    }
                });
    }

    /**
     * In case we need the parent activity
     * @param filter
     * @param activity
     */
    public void bind(StartupFilter filter, Activity activity) {
        this.activity = activity;

        bind(filter);
    }


    /**
     * Shows startups in the list
     *
     * @param startups
     */
    private void setupRecyclerView(List<Startup> startups) {

        if (Val.isNull(adapter)) {

            StartupBinder startupBinder = StartupBinder.instance(getContext());

            adapter = new LeastAdapter.Builder()
                    .binder(startupBinder.setListItemClickListener(this))
                    .items(startups)
                    .build(getContext());

            recyclerView.setDivider(true);
            recyclerView.setAdapter(adapter);
        }

        //replace current items
        else adapter.replace(startups);
    }

    /**
     * List item click listener
     *
     * @param holder
     * @param startup
     * @param i
     */
    @Override
    public void onListItemClick(Holdr_ListItemStartup holder, Startup startup, int i) {

        if (Val.notNull(activity)) {

            //we'll show the activity with a shared element transition
            ActivityStartupDetails.start(activity, startup, holder.itemView);
        }

        else ActivityStartupDetails.start(getContext(), startup);
    }

    /**
     * Ui progressbar
     *
     * @param show
     */
    private void showProgressBar(boolean show) {
        UtilView.show(progressBar, show);
    }

    /*****************************************************
     * ---------------- * Requests * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Api request to retrieve startups based on category. The response is cached
     *
     * @param filter startup category
     *
     * @return
     */
    private Observable<List<Startup>> getStartups(final StartupFilter filter){

        return apiService.startups(filter)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnRequest(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        showProgressBar(true);
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showProgressBar(false);
                    }
                })
                .doOnCompleted(new Action0() {

                    @Override
                    public void call() {
                        showProgressBar(false);
                    }
                })

                .flatMap(new Func1<List<Startup>, Observable<List<Startup>>>() {

                    @Override
                    public Observable<List<Startup>> call(List<Startup> startups) {
                        //cache response
                        return startupCache.put(startups);
                    }
                });
    }


}
