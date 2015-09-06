package io.c0nnector.github.paradise.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.c0nnector.github.paradise.application.Application;
import io.c0nnector.github.paradise.ui.LauncherActivity;
import io.c0nnector.github.paradise.ui.home.MainActivity;
import io.c0nnector.github.paradise.ui.home.tabs.TabView;
import io.c0nnector.github.paradise.ui.person.ActivityPersonDetails;
import io.c0nnector.github.paradise.ui.person.PersonDetailsView;
import io.c0nnector.github.paradise.ui.screenshot.ActivityScreenshot;
import io.c0nnector.github.paradise.ui.search.ActivitySearch;
import io.c0nnector.github.paradise.ui.search.SearchView;
import io.c0nnector.github.paradise.ui.startup.ActivityStartupDetails;
import io.c0nnector.github.paradise.ui.startup.StartupDetailsView;
import io.c0nnector.github.paradise.ui.views.UrlImageView;

@Module(
        includes = {
                DataModule.class
        },

        injects = {

                //activities
                Application.class,
                MainActivity.class,
                ActivityStartupDetails.class,
                ActivityScreenshot.class,
                ActivityPersonDetails.class,
                ActivitySearch.class,
                LauncherActivity.class,

                //views
                TabView.class,
                UrlImageView.class,
                PersonDetailsView.class,
                StartupDetailsView.class,
                SearchView.class
        }
)
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }
}
