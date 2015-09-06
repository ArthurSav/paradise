package io.c0nnector.github.paradise.ui.startup;

import java.util.ArrayList;
import java.util.List;

import io.c0nnector.github.paradise.api.model.Role;
import io.c0nnector.github.paradise.api.model.misc.StartupRole;

/**
 * A list that handles company roles e.g founders, investors etc..
 *
 * @see io.c0nnector.github.paradise.api.model.Role
 */
public class CompanyRolesList extends ArrayList<Role> {

    List<Role> roles;

    public CompanyRolesList(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Depending on the filter it will return a list of people having a certain company role/title E.g founder, investor etc..
     * @param startupRole
     * @return
     */
    public List<Role> getCompanyPeople(StartupRole startupRole){

        String startupRoleStr = startupRole.toString();

        List<Role> roles = new ArrayList<>();

        for (Role role : this.roles) {
            if (role.getRole().equals(startupRoleStr)) roles.add(role);
        }

        return roles;
    }

    /**
     * Return all people from a company
     * @return
     */
    public List<Role> getCompanyPeople(){
        return roles;
    }
}
