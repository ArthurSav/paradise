package io.c0nnector.github.paradise.api.model.misc;


public enum  TagType {

    STARTUP("Startup"),

    USER("User"),

    MARKET("MarketTag"),

    LOCATION("LocationTag");


    final String tagType;

     TagType(String tagType) {
        this.tagType = tagType;
    }

    @Override
    public String toString() {
        return tagType;
    }

    /**
     * Compares a string to a tag type
     * @return
     */
    public boolean isType(String tag){
        return toString().equals(tag);
    }
}
