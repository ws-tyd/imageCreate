package com.jyyy.picturegeneration.pojo.mLol;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor

@Data
public class MlolSkins {

  @JsonProperty("id")
  private String id;
  @JsonProperty("HeroId")
  private String heroId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Quality")
  private String quality;
  @JsonProperty("Image")
  private String image;
  @JsonProperty("QualityFont")
  private String qualityFont;

}
