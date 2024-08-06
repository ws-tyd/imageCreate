package com.jyyy.picturegeneration.pojo.mLol;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor

@NoArgsConstructor

@Data
public class MLolAccounts {

//  // @JsonProperty("Id")
  @NotNull
  private String id;
//  // @JsonProperty("QQ")
  @NotNull
  private String qq;
//  // @JsonProperty("CreatedTime")
  private String createdTime;
  // @JsonProperty("LatestUpdatedTime")
  private String latestUpdatedTime;
  // @JsonProperty("Rank")
  private String rank;
  // @JsonProperty("ServerArea")
  private String serverArea;
  // @JsonProperty("ServerAreaId")
  private long serverAreaId;
  // @JsonProperty("HeroesAmount")
  private long heroesAmount;
  // @JsonProperty("SkinsAmount")
  private long skinsAmount;

}
