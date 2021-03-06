/**
 * File generated by Magnet rest2mobile 1.1 - Aug 14, 2015 10:47:06 AM
 *
 * @see {@link http://developer.magnet.com}
 */

package io.c0nnector.github.paradise.api.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.c0nnector.github.paradise.api.model.misc.TagType;

/**
 * Generated from json example
 {
 "type" : "User",
 "name" : "JoyCampbell",
 "id" : 1450871,
 "bio" : null,
 "follower_count" : 1,
 "angellist_url" : "https://angel.co/joy-campbell",
 "image" : "https://angel.co/images/shared/nopic.png"
 }

 */

@Parcel
public class Tagged {


    String type;

    String name;

    Integer id;

    String bio;

    Boolean hidden;

    Boolean community_profile;

    String logo_url;

    String thumb_url;

    Integer quality;

    String product_desc;

    String high_concept;

    @SerializedName("follower_count")
    Integer followerCount;

    @SerializedName("angellist_url")
    String angelistUrl;

    String image;

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public Integer getId() {

        return id;
    }

    public String getBio() {

        return bio;
    }

    public Integer getFollowerCount() {

        return followerCount;
    }

    public String getAngelistUrl() {

        return angelistUrl;
    }

    public String getImage() {

        return image;
    }

    public String getLogo_url() {

        return logo_url;
    }

    public String getThumb_url() {

        return thumb_url;
    }

    public String getProduct_desc() {

        return product_desc;
    }

    public String getHigh_concept() {

        return high_concept;
    }

    /**
     * Checks if the object is of a certain tag type
     *
     * @param type
     */
    public boolean isType(TagType type) {
        return type.isType(this.type);
    }



}
