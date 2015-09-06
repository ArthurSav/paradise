package io.c0nnector.github.paradise.api.model.misc;

/**
 * Startup roles
 */
public enum StartupRole {

    FOUNDER("founder"),

    PAST_INVESTOR("past_investor"),

    ADVISOR("advisor"),

    BOARD_MEMBER("board_member"),

    INCUBATOR("incubator"),

    EMPLOYEE("employee");

    final String role;

     StartupRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
