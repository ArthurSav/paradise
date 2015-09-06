package io.c0nnector.github.paradise.api;

import java.util.List;

import io.c0nnector.github.paradise.api.model.Person;
import io.c0nnector.github.paradise.api.model.SearchItem;
import io.c0nnector.github.paradise.api.model.misc.StartupFilter;
import io.c0nnector.github.paradise.api.model.misc.StartupRole;
import io.c0nnector.github.paradise.api.model.misc.TagType;
import io.c0nnector.github.paradise.api.model.results.RolesResult;
import io.c0nnector.github.paradise.api.model.results.Startup;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Add all api calls here
 */
public interface ApiService {

    /**
     * Get startups based on filter search e.g new, hiring etc..
     * @param filter
     * @return
     */
    @GET("/startups")
    Observable<List<Startup>> startups(@Query("filter") StartupFilter filter);

    /**
     * Request a startup
     * @param startupId
     * @return
     */
    @GET("/startups/{id}")
    Observable<Startup> startup(@Path("id") Integer startupId);

    /**
     * Startup roles
     * @param startupId
     * @param role a specific type of role to request e.g founder
     * @return
     */
    @GET("/startups/{id}/roles")
    Observable<RolesResult> startupRoles(@Path("id") Integer startupId, @Query("role") StartupRole role);

    /**
     * Startup roles
     * @param startupId
     * @return
     */
    @GET("/startups/{id}/roles")
    Observable<RolesResult> startupRoles(@Path("id") Integer startupId);

    /**
     * Get a user
     * @param userId
     * @return
     */
    @GET("/users/{id}")
    Observable<Person> person(@Path("id") Integer userId);

    /**
     * Get a user roles
     * @param userId
     * @return
     */
    @GET("/users/{id}/roles")
    Observable<RolesResult> personRoles(@Path("id") Integer userId);

    /**
     * Search for startups, people etc..
     * @param query
     * @return
     */
    @GET("/search")
    Observable<List<SearchItem>> search(@Query("query") String query);


    /**
     * Search using a certain type e.g locationTag
     * @param query
     * @param type check out
     * @return
     */
    @GET("/search")
    Observable<List<SearchItem>> search(@Query("query") String query, @Query("type") TagType type);
}
