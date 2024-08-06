package com.jyyy.picturegeneration.pojo.mLol;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor

@Data
public class MLolHeroes {

 // @JsonProperty("Id")
  private String id;
 // @JsonProperty("Name")
  private String name;
 // @JsonProperty("Image")
  private String image;
 // @JsonProperty("Title")
  private String title;
 // @JsonProperty("KeyWords")
  private String keyWords;

}
