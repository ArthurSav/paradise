package io.c0nnector.github.paradise.ui.person;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.ApiService;
import io.c0nnector.github.paradise.api.RetroSubscriber;
import io.c0nnector.github.paradise.api.Transformers;
import io.c0nnector.github.paradise.api.model.Person;
import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.SearchItem;
import io.c0nnector.github.paradise.api.model.Tagged;
import io.c0nnector.github.paradise.api.model.misc.StartupRole;
import io.c0nnector.github.paradise.api.model.results.RolesResult;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.binders.StartupLightBinder;
import io.c0nnector.github.paradise.holdr.Holdr_ListItemStartupLight;
import io.c0nnector.github.paradise.ui.dialogs.StartupsDialog;
import io.c0nnector.github.paradise.ui.startup.ActivityStartupDetails;
import io.c0nnector.github.paradise.ui.startup.CompanyRolesList;
import io.c0nnector.github.paradise.ui.views.BaseLinearLayout;
import io.c0nnector.github.paradise.ui.views.SocialButtonsView;
import io.c0nnector.github.paradise.util.UtilMeasure;
import io.c0nnector.github.paradise.util.UtilView;
import io.c0nnector.github.least.LeastAdapter;
import io.c0nnector.github.least.LeastView;
import io.c0nnector.github.least.ListItemListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class PersonDetailsView extends BaseLinearLayout {

    /**
     * The min number of items in order to display a 'see all' button (investments or advisories)
     */
    public static final int MIN_SEE_ALL_ITEMS = 3;


    @Inject
    ApiService apiService;


    @Bind(R.id.vPersonHeader)
    PersonHeaderView personHeaderView;

    @Bind(R.id.vSocialButtons)
    SocialButtonsView socialButtonsView;

    @Bind(R.id.foundedLeastView)
    LeastView foundedLeastView;

    @Bind(R.id.investedLeastView)
    LeastView investedLeastView;

    @Bind(R.id.advisorLeastView)
    LeastView advisorLeastView;

    @Bind(R.id.vFounded)
    RelativeLayout vFounded;

    @Bind(R.id.vInvested)
    RelativeLayout vInvested;

    @Bind(R.id.vAdvisor)
    RelativeLayout vAdvisor;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.btnInvestedAll)
    TextView btnSeeAllInvested;

    @Bind(R.id.btnAdvisorAll)
    TextView btnSeeAllAdvised;

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public PersonDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) Application.get(getContext()).inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        fixCoordinatorLayoutBug();
    }

    /**
     * Shows partial data & requests full info
     *
     * @param item
     */
    public void bind(SearchItem item){

        Integer userId = item.getId();


        personHeaderView.setupBasicInfo(item.getUrl(), item.getName(), "...");

        requestFullUserInfo(userId);

        requestUserRoles(userId);
    }

    /**
     * Shows partial data & requests full info
     *
     * @param role
     */
    public void bind(Role role) {

        Tagged tagged = role.getTagged();


        personHeaderView.setupBasicInfo(tagged.getImage(), tagged.getName(), tagged.getBio());

        requestFullUserInfo(tagged.getId());

        requestUserRoles(tagged.getId());
    }

    /**
     * Shows full user info
     *
     * @param person
     */
    public void bind(Person person) {

        personHeaderView.setupBasicInfo(person.getImage(), person.getName(), person.getBio());

        personHeaderView.setupSecondaryInfo(person.getFirtLocationName(), person.getFirstRoleTag());

        socialButtonsView.bind(person);
    }


    /**
     * Bind roles to a listview
     *
     * @param roles e.g adviser
     */
    private void bind(List<Role> roles) {

        //Original container height is 0. We add new height in order to animate the height change
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, UtilMeasure.dpToPx(150));

        //animate layout changes
        UtilView.delayedTransition(vFounded, vInvested, vAdvisor);


        CompanyRolesList companyRolesList = new CompanyRolesList(roles);

        List<Role> foundedCompanies = companyRolesList.getCompanyPeople(StartupRole.FOUNDER);
        List<Role> investedCompanies = companyRolesList.getCompanyPeople(StartupRole.PAST_INVESTOR);
        List<Role> advisorCompanies = companyRolesList.getCompanyPeople(StartupRole.ADVISOR);


        //companies founded
        if (UtilView.show(vFounded, foundedCompanies.size() > 0)){

            showRoles(foundedLeastView, foundedCompanies);
            vFounded.setLayoutParams(params);
        }

        //companies invested in
        if (UtilView.show(vInvested, investedCompanies.size() > 0)){

            showRoles(investedLeastView, investedCompanies);
            vInvested.setLayoutParams(params);
        }

        //companies advising at
        if (UtilView.show(vAdvisor, advisorCompanies.size() > 0)){

            showRoles(advisorLeastView, advisorCompanies);
            vAdvisor.setLayoutParams(params);
        }


        //'see all' buttons
        setupSeeAllButtons(investedCompanies, advisorCompanies);


    }


    /*****************************************************
     * ---------------- * User * --------------------
     *
     *
     *
     ****************************************************/

    /**
     * Retrieves full user info
     *
     * @param userId
     */
    private void requestFullUserInfo(Integer userId) {

        apiService.person(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userSubscriber);
    }

    private Subscriber<Person> userSubscriber = new RetroSubscriber<Person>() {

        @Override
        public void onNext(Person person) {
            super.onNext(person);

            bind(person);
        }
    };

    /*****************************************************
     * ---------------- * Roles * --------------------
     ****************************************************/

    private void requestUserRoles(Integer userId) {

        apiService.personRoles(userId)
                .compose(Transformers.<RolesResult>addProgress(progressBar))
                .subscribe(rolesResult);
    }

    private Subscriber<RolesResult> rolesResult = new RetroSubscriber<RolesResult>() {

        @Override
        public void onNext(RolesResult rolesResult) {
            super.onNext(rolesResult);

            bind(rolesResult.getRoles());
        }
    };

    /**
     * Handles the 'see all' buttons for invested & advised companies
     */
    private void setupSeeAllButtons(final List<Role> invested, final List<Role> advised) {

        if (UtilView.show(btnSeeAllInvested, invested.size() > MIN_SEE_ALL_ITEMS)) {

            btnSeeAllInvested.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    StartupsDialog.show(getContext(), invested);
                }
            });
        }

        if (UtilView.show(btnSeeAllAdvised, advised.size() > MIN_SEE_ALL_ITEMS)) {

            btnSeeAllAdvised.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    StartupsDialog.show(getContext(), advised);
                }
            });
        }
    }

    /**
     * Displays companies(light version) in a horizontal list view
     *
     * @param leastView
     * @param roles
     */
    private void showRoles(LeastView leastView, List<Role> roles) {

        StartupLightBinder startupBinder = StartupLightBinder.instance();

        LeastAdapter adapter = new LeastAdapter.Builder()

                .binder(startupBinder.setListItemClickListener(companyClickListener))

                .items(roles)

                .build(getContext());

        leastView.setAdapter(adapter);
        leastView.horizontal(true);
    }

    /**
     * Roles list item listener
     */
    private ListItemListener<Holdr_ListItemStartupLight, Role> companyClickListener = new ListItemListener<Holdr_ListItemStartupLight, Role>() {

        @Override
        public void onListItemClick(Holdr_ListItemStartupLight holdr_listItemStartupLight, Role role, int i) {
            ActivityStartupDetails.start(getContext(), role);
        }
    };


    /*****************************************************
     * ---------------- * Google fixes * --------------------
     *
     *
     *
     ****************************************************/

    //// TODO: remove this when there's a fix in the design lib. Current version: v23.0.0
    private void fixCoordinatorLayoutBug(){

        //nested scrolling bug 'solution'
        investedLeastView.setNestedScrollingEnabled(false);
        foundedLeastView.setNestedScrollingEnabled(false);
        advisorLeastView.setNestedScrollingEnabled(false);
    }

}
