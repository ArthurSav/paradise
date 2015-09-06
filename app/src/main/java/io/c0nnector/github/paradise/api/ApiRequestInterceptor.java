package io.c0nnector.github.paradise.api;

import retrofit.RequestInterceptor;

/**
 * Intercepts api calls
 */
public class ApiRequestInterceptor implements RequestInterceptor {

    String token;

    public ApiRequestInterceptor(String token) {
        this.token = token;
    }

    @Override
    public void intercept(RequestFacade request) {

        //api token
        request.addQueryParam("access_token", token);
    }



}
