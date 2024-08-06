package com.jyyy.picturegeneration.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MLOLConfig {

    private String gameId;
    private String boxWidth;
    private String boxHeight;
    private String imageWidth;
    private String imageHeight;
    private String startX;
    private String startY;
    private String cloNumber;
    private String rowNumber;
}
