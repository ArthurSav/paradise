package io.c0nnector.github.paradise.api.model;

import org.parceler.Parcel;

import io.c0nnector.github.paradise.api.model.misc.TagType;


@Parcel
public class SearchItem {

    Integer id;

    String pic;

    String url;

    String name;

    String type;

    public Integer getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getUrl() {

        return url;
    }

    public String getName() {

        return name;
    }

    public String getType() {

        return type;
    }

    /**
     * Checks if the object is of a certain tag type
     *
     * @param type
     */
    public boolean isType(TagType type) {
        return type.isType(this.type);
    }

    public boolean isTypeUser(){
        return isType(TagType.USER);
    }

    public boolean isTypeStartup(){
        return isType(TagType.STARTUP);
    }

}
