package io.c0nnector.github.paradise.ui.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.transitionseverywhere.TransitionManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.ApiService;
import io.c0nnector.github.paradise.api.model.SearchItem;
import io.c0nnector.github.paradise.api.model.misc.TagType;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.binders.SearchItemBinder;
import io.c0nnector.github.paradise.bus.SearchViewCloseEvent;
import io.c0nnector.github.paradise.holdr.Holdr_PartStartupPerson;
import io.c0nnector.github.paradise.ui.person.ActivityPersonDetails;
import io.c0nnector.github.paradise.ui.startup.ActivityStartupDetails;
import io.c0nnector.github.paradise.ui.views.BaseRelativeLayout;
import io.c0nnector.github.paradise.util.UtilTextView;
import io.c0nnector.github.paradise.util.UtilView;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;
import io.c0nnector.github.least.ListItemListener;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action0;
import rx.functions.Action1;


public class SearchView extends BaseRelativeLayout {

    LeastAdapter leastAdapter;

    @Inject
    ApiService apiService;


    @Bind(R.id.recyclerView)
    LeastView leastView;

    @Bind(R.id.txtSearch)
    EditText txtSearch;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.vSearchContainer)
    ViewGroup vSearchContainer;


    /**
     * Close button
     *
     * - Clears the textview when non empty
     * - Closes the view when textview is empty
     */
    @OnClick(R.id.btnClose)
    public void onBtnClose(){

        if (!UtilTextView.with(txtSearch).clear()) Application.post(new SearchViewCloseEvent());

        else onClearTextview();
    }


    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) Application.get(getContext()).inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setupRecyclerview();

        setupSearch();
    }

    /**
     * We use a custom layout manager that wraps the recyclerview.
     * By default wrap_content does not work correctly
     */
    private void setupRecyclerview(){

        leastView.enableContentWrap(true);
        leastView.setDivider(true);
    }

    /**
     * Search text observable
     * We use rxjava to observe for search queries
     */
    private void setupSearch(){

        Observable<OnTextChangeEvent> searchObservable = WidgetObservable.text(txtSearch);

        searchObservable

                //delay search to avoid fast-type http calls
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OnTextChangeEvent>() {

                    @Override
                    public void call(OnTextChangeEvent onTextChangeEvent) {

                        search(onTextChangeEvent.text().toString());
                    }
                });

    }

    /**
     * Bind user list to show
     * @param searchItems
     */
    public void bind(List<SearchItem> searchItems){

        TransitionManager.beginDelayedTransition(vSearchContainer);

        if (Val.isNull(leastAdapter)) {

            SearchItemBinder binder = SearchItemBinder.instance();

             leastAdapter = new LeastAdapter.Builder()
                    .binder(binder.setListItemClickListener(searchListener))
                    .items(searchItems)
                    .build(getContext());

            leastView.setAdapter(leastAdapter);
        }

        else leastAdapter.replace(searchItems);
    }

    /**
     * List item click listener
     */
    private ListItemListener<Holdr_PartStartupPerson, SearchItem> searchListener = new ListItemListener<Holdr_PartStartupPerson, SearchItem>() {

        @Override
        public void onListItemClick(Holdr_PartStartupPerson holdr_partStartupPerson, SearchItem searchItem, int i) {

            //open user profile
            if (searchItem.isType(TagType.USER)) ActivityPersonDetails.start(getContext(), searchItem);

                //open startup profile
            else if (searchItem.isType(TagType.STARTUP)) ActivityStartupDetails.start(getContext(), searchItem.getId());
        }
    };

    /**
     * Search for a keyword
     * @param query
     */
    private void search(String query){

        //ignore empty queries
        if (query.isEmpty()) return;

        apiService.search(query)

                .observeOn(AndroidSchedulers.mainThread())

                .doOnRequest(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {
                        showLoader(true);
                    }
                })

                .doOnCompleted(new Action0() {

                    @Override
                    public void call() {
                        showLoader(false);
                    }
                })

                .doOnError(new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {

                    }
                })

                .subscribe(new Action1<List<SearchItem>>() {

                    @Override
                    public void call(List<SearchItem> searchItems) {
                        bind(searchItems);
                    }
                });
    }


    /**
     * Show/hide progress loader
     * @param show
     */
    private void showLoader(boolean show){

        if (!show) UtilView.makeInvisible(progressBar);

        else UtilView.show(progressBar);
    }

    /**
     * Removes items from the list & hides the progressbar
     */
    private void onClearTextview(){

        TransitionManager.beginDelayedTransition(vSearchContainer);

        leastAdapter.removeAll();
        UtilView.hide(progressBar);
    }

}
