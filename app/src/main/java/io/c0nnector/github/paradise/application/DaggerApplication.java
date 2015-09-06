package io.c0nnector.github.paradise.application;

import java.util.Objects;

import dagger.ObjectGraph;

/**
 * Helper class to implement dagger
 */
public class DaggerApplication extends android.app.Application {

    protected ObjectGraph objectGraph;

    /**
     * Call it onCreate() to setup dagger
     *
     * @param modules
     */
    protected void setupDagger(Object[] modules) {

        objectGraph = ObjectGraph.create(modules);
        objectGraph.inject(this);
    }

    /**
     * Inject an activity to the graph
     *
     * @param object
     */
    public void inject(Object object) {
        getObjectGraph().inject(object);
    }

    public void createScopedGraph(Objects... modules){
        getObjectGraph().plus(modules);
    }


    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
