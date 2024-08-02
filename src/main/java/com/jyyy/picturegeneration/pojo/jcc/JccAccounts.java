package com.jyyy.picturegeneration.pojo.jcc;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor

@Data
public class JccAccounts {

  @JsonProperty("Id")
  private String id;
  @JsonProperty("QQ")
  private String qq;
  @JsonProperty("CreatedTime")
  private String createdTime;
  @JsonProperty("LatestUpdatedTime")
  private String latestUpdatedTime;
  @JsonProperty("Rank")
  private String rank;
  @JsonProperty("ServerArea")
  private String serverArea;
  @JsonProperty("ServerAreaId")
  private long serverAreaId;
  @JsonProperty("HeroesAmount")
  private long heroesAmount;
  @JsonProperty("SkinsAmount")
  private long skinsAmount;

}
