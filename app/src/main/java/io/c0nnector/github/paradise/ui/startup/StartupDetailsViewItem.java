package io.c0nnector.github.paradise.ui.startup;

import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.results.Startup;

/**
 * Binder items passed to the startupview
 */
public class StartupDetailsViewItem {

    public Startup startup;

    public Role role;

    public Integer startupId;


    public StartupDetailsViewItem(Startup startup) {
        this.startup = startup;
    }

    public StartupDetailsViewItem(Role role) {
        this.role = role;
    }

    public StartupDetailsViewItem(Integer startupId) {
        this.startupId = startupId;
    }

    public StartupDetailsViewItem(Startup startup, Role role, Integer startupId) {
        this.startup = startup;
        this.role = role;
        this.startupId = startupId;
    }
}
