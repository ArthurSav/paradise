/**
 * File generated by Magnet rest2mobile 1.1 - Aug 16, 2015 6:48:08 PM
 * @see {@link http://developer.magnet.com}
 */

package io.c0nnector.github.paradise.api.model;


import org.parceler.Parcel;

/**
 * Generated from json example
{
  "id" : 81458,
  "tag_type" : "SkillTag",
  "name" : "seed&venturecapital",
  "display_name" : "Seed&VentureCapital",
  "angellist_url" : "https://angel.co/seed-venture-capital-1",
  "level" : "expert"
}

 */

@Parcel
public class SkillTag {

  
  private String angellist_url;

  
  private String display_name;

  
  private Integer id;

  
  private String level;

  
  private String name;

  
  private String tag_type;

  public String getAngellist_url() {
    return angellist_url;
  }
  public String getDisplay_name() {
    return display_name;
  }
  public Integer getId() {
    return id;
  }
  public String getLevel() {
    return level;
  }
  public String getName() {
    return name;
  }
  public String getTag_type() {
    return tag_type;
  }


}
