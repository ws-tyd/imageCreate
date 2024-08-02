package com.jyyy.picturegeneration.controller.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jyyy.picturegeneration.pojo.jcc.JccAccounts;
import com.jyyy.picturegeneration.pojo.jcc.JccHeroes;
import com.jyyy.picturegeneration.pojo.jcc.JccSkins;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JccMainPictureParam {
    public JccAccounts account;

    public List<JccSkins> skins;

    public List<JccHeroes> heroes;
}
