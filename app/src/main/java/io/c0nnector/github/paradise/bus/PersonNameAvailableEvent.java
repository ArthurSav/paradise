package io.c0nnector.github.paradise.bus;

/**
 * Created by arthur on 8/27/15.
 */
public class PersonNameAvailableEvent {

    String username;

    public PersonNameAvailableEvent(String username) {

        this.username = username;
    }

    public String getUsername() {

        return username;
    }
}
