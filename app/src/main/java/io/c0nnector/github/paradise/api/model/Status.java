/**
 * File generated by Magnet rest2mobile 1.1 - Jul 31, 2015 3:08:28 PM
 * @see {@link http://developer.magnet.com}
 */

package io.c0nnector.github.paradise.api.model;


import org.parceler.Parcel;

/**
 * Generated from json example
{
  "id" : 75020,
  "message" : "",
  "created_at" : "2012-10-15T10:54:03Z"
}

 */

@Parcel
public class Status {

  private String created_at;
  
  private Integer id;
  
  private String message;

  public String getCreated_at() {
    return created_at;
  }
  public Integer getId() {
    return id;
  }
  public String getMessage() {
    return message;
  }


}
