package io.c0nnector.github.paradise.ui.startup;

/**
 * Settings to pass to the startup view
 */
public class StartupDetailsViewSettings {

    /**
     * If true, we will request startup roles onBind()
     */
    private boolean requestStartupRoles = false;

    /**
     * If true, we will request a full startup object update onBind()
     */
    private boolean requestFullStartupObject = false;

    /**
     * If true, we won't set all info since we have a partial data
     */
    private boolean partialInfo = false;


    public StartupDetailsViewSettings requestRole(boolean request){
        this.requestStartupRoles = request;
        return this;
    }

    public StartupDetailsViewSettings requestStartup(boolean request){
        this.requestFullStartupObject = request;
        return this;
    }

    public StartupDetailsViewSettings setPartialInfo(boolean isPartial){
        this.partialInfo = isPartial;
        return this;
    }


    public boolean isRequestStartupRolesEnabled() {
        return requestStartupRoles;
    }

    public boolean isRequestFullStartupObjectEnabled() {
        return requestFullStartupObject;
    }

    public boolean isParcialnfo(){
        return partialInfo;
    }


    /*****************************************************
     * ---------- * Predefined settings * ---------
     *
     *
     *
     ****************************************************/

    /**
     * Settings for when we bind a full startup object and we need to request it's roles
     * @return
     */
    public static StartupDetailsViewSettings getDefaultSettings(){

        return  new StartupDetailsViewSettings()
                .requestRole(true)
                .requestStartup(false)
                .setPartialInfo(false);
    }

    /**
     * Settings for when we bind a partial startup object
     * @return
     */
    public static StartupDetailsViewSettings getPartialLoadSettings(){

        return  new StartupDetailsViewSettings()
                .requestRole(true)
                .requestStartup(true)
                .setPartialInfo(true);
    }

    /**
     * Settings when we want to bind full objects with no additional network calls
     * @return
     */
    public static StartupDetailsViewSettings getAfterLoadStartupSettings(){

        return  new StartupDetailsViewSettings()
                .requestRole(false)
                .requestStartup(false)
                .setPartialInfo(false);
    }

}
