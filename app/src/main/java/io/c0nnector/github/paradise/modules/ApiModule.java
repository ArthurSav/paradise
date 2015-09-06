package io.c0nnector.github.paradise.modules;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.c0nnector.github.paradise.BuildConfig;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.paradise.api.ApiRequestInterceptor;
import io.c0nnector.github.paradise.api.ApiService;
import io.c0nnector.github.paradise.application.Application;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import static java.util.concurrent.TimeUnit.SECONDS;

@Module(
        complete = false,
        library = true
)
public class ApiModule {

    public static final String PRODUCTION_API_URL = "https://api.angel.co/1";


    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return createOkHttpClient();
    }

    @Provides
    @Singleton
    @Named("apiToken")
    String providesApiToken(Application application){
        return application.getResources().getString(R.string.token_angelist_api);
    }


    @Provides
    @Singleton
    ApiRequestInterceptor providerequestInterceptor(@Named("apiToken") String token) {
        return new ApiRequestInterceptor(token);
    }


    @Provides
    @Singleton
    RestAdapter providesRestAdapter(OkHttpClient okHttpClient, Endpoint endpoint, ApiRequestInterceptor interceptor, Gson gson) {

        return new RestAdapter.Builder()

                .setClient(new OkClient(okHttpClient))

                .setEndpoint(endpoint)

                .setConverter(new GsonConverter(gson))

                .setRequestInterceptor(interceptor)

                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)

                .build();
    }

    @Provides
    @Singleton
    ApiService provideMyApiService(RestAdapter restAdapter) {
        return restAdapter.create(ApiService.class);
    }


    /**
     * Creates an http client for our api
     *
     * @return
     */
    static OkHttpClient createOkHttpClient() {

        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(15, SECONDS);
        client.setReadTimeout(15, SECONDS);
        client.setWriteTimeout(15, SECONDS);

        return client;
    }
}
