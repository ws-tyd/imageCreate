package com.jyyy.picturegeneration.controller.param;

import com.jyyy.picturegeneration.pojo.mLol.MLolAccounts;
import com.jyyy.picturegeneration.pojo.mLol.MLolHeroes;
import com.jyyy.picturegeneration.pojo.mLol.MlolSkins;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MLolMainPictureParam {

    public MLolAccounts account;

    public List<MlolSkins> skins;

    public List<MLolHeroes> heroes;


    public Map<String, Object> buildDataMap() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("QQ:", this.account.getQq());
        dataMap.put("英雄数量:", this.account.getHeroesAmount());
        dataMap.put("皮肤数量:", this.account.getSkinsAmount());
        dataMap.put("游戏区服:", this.account.getServerArea());
        dataMap.put("可二次:", "");
        return dataMap;
    }
}
