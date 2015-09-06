package io.c0nnector.github.paradise.ui.startup;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.ApiService;
import io.c0nnector.github.paradise.api.RetroSubscriber;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.Screenshot;
import io.c0nnector.github.paradise.api.model.Tagged;
import io.c0nnector.github.paradise.api.model.misc.StartupRole;
import io.c0nnector.github.paradise.api.model.misc.TagType;
import io.c0nnector.github.paradise.api.model.results.RolesResult;
import io.c0nnector.github.paradise.api.model.results.Startup;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.binders.CompanyRoleBinder;
import io.c0nnector.github.paradise.binders.StartupBinder;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartup;
import io.c0nnector.github.paradise.holdr.Holdr_PartStartupPerson;
import io.c0nnector.github.paradise.ui.person.ActivityPersonDetails;
import io.c0nnector.github.paradise.ui.startup.photos.PagerAdapter;
import io.c0nnector.github.paradise.ui.views.BaseLinearLayout;
import io.c0nnector.github.paradise.ui.views.ScreenshotViewPager;
import io.c0nnector.github.paradise.ui.views.SocialButtonsView;
import io.c0nnector.github.paradise.util.UtilView;
import io.c0nnector.github.paradise.util.Val;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;
import io.c0nnector.github.least.ListItemListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class StartupDetailsView extends BaseLinearLayout {

    /**
     * Settings to adjust behaviour
     */
    StartupDetailsViewSettings settings;

    FragmentManager fragmentManager;

    Startup startup;

    List<Role> companyPeople;

    Holdr_ListItemStartup holderHeader;


    @Inject
    ApiService apiService;


    @Bind(R.id.mainContentContainer)
    LinearLayout mainContainer;


    @Bind(R.id.vSocialButtons)
    SocialButtonsView socialButtonsView;

    @Bind(R.id.vBasicInfo)
    View vBasicInfo;

    @Bind(R.id.vContacts)
    RelativeLayout vContactsContainer;


    @Bind(R.id.vPager)
    ScreenshotViewPager viewPager;


    @Bind(R.id.vRecyclerviewFounders)
    LeastView leastViewFounders;

    @Bind(R.id.vRecyclerviewInvestors)
    LeastView leastViewInvestors;



    @Bind(R.id.vFoundersContainer)
    RelativeLayout vFoundersContainer;

    @Bind(R.id.vInvestorsContainer)
    RelativeLayout vInvestorsContainer;



    @Bind(R.id.progressBar)
    ProgressBar progressBar;


    @Bind(R.id.vDescription)
    StartupDescriptionView vDescription;


    @OnClick(R.id.vDescription)
    public void onReadMore(){
        vDescription.toggle();
    }


    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public StartupDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) Application.get(getContext()).inject(this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.holderHeader = new Holdr_ListItemStartup(vBasicInfo);

        fixCoordinatorLayoutBug();
    }


    /**
     * Bind startup view
     *
     * @param fragmentManager
     * @param startup  full startup object
     * @param settings behaviour settings
     *
     * @see StartupDetailsViewSettings
     */
    private void bind(FragmentManager fragmentManager, Startup startup, StartupDetailsViewSettings settings) {
        this.fragmentManager = fragmentManager;
        this.settings = settings;

        animateBounds(mainContainer);

        //bind basic info
        StartupBinder.bind(getContext(), holderHeader, startup);


        //description
        vDescription.bind(startup.getProduct_desc());


        //only for full startup object
        if (!settings.isParcialnfo()) {
            this.startup = startup;

            //screenshots
            setupScreenshots(fragmentManager, startup.getScreenshots());

            //social buttons
            socialButtonsView.bind(startup);
        }

        //request full startup object
        if (settings.isRequestFullStartupObjectEnabled()) requestStartup(startup.getId());

        //request startup roles
        if (settings.isRequestStartupRolesEnabled()) requestStartupRoles(startup.getId());
    }

    /**
     * Bind with default settings behaviour
     * @param fragmentManager
     * @param startup
     */
    public void bind(FragmentManager fragmentManager, Startup startup) {
        bind(fragmentManager, startup, StartupDetailsViewSettings.getDefaultSettings());
    }

    /**
     * Bind startup view, with partial info.
     *
     * When the role->tagged is of type "USER" then we use the role->startup object to fill the partial data
     * If the role->tagged is of type "STARTUP" then we construct a partial object from the tagged object
     * @see Tagged
     * @see Startup
     *
     * @param fragmentManager
     * @param role
     */
    public void bind(FragmentManager fragmentManager, Role role) {

        Tagged tagged = role.getTagged();

        Startup startup;

        //info is located in role->tagged
        if (tagged.isType(TagType.STARTUP)) startup = Startup.convertFromTagged(tagged);

        //info we need is located in role->startup
        else startup = role.getStartup();


        bind(fragmentManager, startup, StartupDetailsViewSettings.getPartialLoadSettings());
    }

    /**
     * Bind only the startupId, request everything else
     *
     * @param fragmentManager
     * @param startupId startup id to show
     */
    public void bind(FragmentManager fragmentManager, Integer startupId){
        this.fragmentManager = fragmentManager;

        requestStartup(startupId);

        requestStartupRoles(startupId);
    }


    /**
     * Startup screenshots
     */
    private void setupScreenshots(FragmentManager manager, List<Screenshot> screenshots) {

        if (UtilView.show(viewPager, screenshots.size() > 0)) {

            PagerAdapter adapter = new PagerAdapter(manager, screenshots);
            viewPager.setAdapter(adapter);
        }
    }


    /*****************************************************
     * ---------------- * Startup * --------------------
     ****************************************************/

    private void requestStartup(Integer startupId) {

        //get startup roles
        apiService.startup(startupId)
                .observeOn(AndroidSchedulers.mainThread())

                .doOnRequest(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {

                        validateLoader();
                    }
                })
                .doOnError(new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {

                        validateLoader();
                    }
                })

                .doOnCompleted(new Action0() {

                    @Override
                    public void call() {

                        validateLoader();
                    }
                })
                .subscribe(subscriberStartup);
    }

    private Subscriber<Startup> subscriberStartup = new RetroSubscriber<Startup>() {

        @Override
        public void onNext(Startup startup) {
            super.onNext(startup);

            //update startup object in the parent activity
            Application.post(startup);

            //show full info
            bind(fragmentManager, startup, StartupDetailsViewSettings.getAfterLoadStartupSettings());
        }
    };


    /*****************************************************
     * ---------------- * Roles * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Api call to request additional startup roles
     *
     * @param startupId
     */
    private void requestStartupRoles(int startupId) {

        //get startup roles
        apiService.startupRoles(startupId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnRequest(new Action1<Long>() {

                    @Override
                    public void call(Long aLong) {

                        validateLoader();
                    }
                })
                .doOnError(new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        validateLoader();
                    }
                })

                .doOnCompleted(new Action0() {

                    @Override
                    public void call() {
                        validateLoader();
                    }
                })                .subscribe(subscriber);
    }

    private Subscriber<RolesResult> subscriber = new RetroSubscriber<RolesResult>() {

        @Override
        public void onNext(RolesResult rolesResult) {
            super.onNext(rolesResult);

            setupCompanyPeople(rolesResult.getRoles());
        }
    };

    /**
     * Show people associated with the company
     *
     * @param companyPeople
     */
    private void setupCompanyPeople(List<Role> companyPeople) {
        this.companyPeople = companyPeople;

        animateBounds(mainContainer);


        //specialized list to handle company people
        CompanyRolesList companyRoles = new CompanyRolesList(companyPeople);

        List<Role> founders = companyRoles.getCompanyPeople(StartupRole.FOUNDER);
        List<Role> investors = companyRoles.getCompanyPeople(StartupRole.PAST_INVESTOR);


        //show/hide founders view
        if (UtilView.show(vFoundersContainer, founders.size() > 0)) {

            setupFounders(founders);
        }

        //show/hide investors view
        if (UtilView.show(vInvestorsContainer, investors.size() > 0)) {

            setupPastInvestors(investors);
        }
    }

    /**
     * Show company founders
     *
     * @param founders
     */
    private void setupFounders(List<Role> founders) {
        showRoles(leastViewFounders, founders);
    }

    /**
     * Show company investors
     *
     * @param investors
     */
    private void setupPastInvestors(List<Role> investors) {
        showRoles(leastViewInvestors, investors);
    }

    /**
     * Displays any type of start role list, into a listview
     * @param leastView
     * @param roles
     */
    private void showRoles(LeastView leastView, List<Role> roles){

        CompanyRoleBinder companyRoleBinder = CompanyRoleBinder.instance(getContext());

        //adapter
        LeastAdapter adapter = new LeastAdapter.Builder()

                .binder(companyRoleBinder.setListItemClickListener(companyPersonClickListener))

                .items(roles)

                .build(getContext());

        leastView.setAdapter(adapter);
        leastView.horizontal(true);
    }

    /**
     * List item listener for founder
     */
    private ListItemListener<Holdr_PartStartupPerson, Role> companyPersonClickListener = new ListItemListener<Holdr_PartStartupPerson, Role>() {

        @Override
        public void onListItemClick(Holdr_PartStartupPerson holdr_partStartupFounder, Role role, int i) {

            Tagged tagged = role.getTagged();

            //open user profile
            if (tagged.isType(TagType.USER)) ActivityPersonDetails.start(getContext(), role);

            //open startup profile
            else if (tagged.isType(TagType.STARTUP)) ActivityStartupDetails.start(getContext(), role);
        }
    };


    /*****************************************************
     * ---------------- * Other * --------------------
     ****************************************************/

    /**
     * Will hide the ui loader only if all requested items are retrieved
     */
    private void validateLoader() {

        //hide loader
        UtilView.show(progressBar, Val.isNull(startup) || Val.isNull(companyPeople));
    }


    /**
     * Basic transition animation
     * @param view
     */
    private void animateBounds(ViewGroup view){

        Transition changeBounds = new ChangeBounds();
        TransitionManager.beginDelayedTransition(view, changeBounds);
    }


    /*****************************************************
     * ---------------- * Google fixes * --------------------
     *
     *
     *
     ****************************************************/

    //// TODO: remove this when there's a fix in the design lib. Current version: v23.0.0
    private void fixCoordinatorLayoutBug(){

        //nested scrolling bug 'solution'
        leastViewFounders.setNestedScrollingEnabled(false);
        leastViewInvestors.setNestedScrollingEnabled(false);
    }
}
