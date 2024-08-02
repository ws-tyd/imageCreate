package com.jyyy.picturegeneration.service;

import com.jyyy.picturegeneration.controller.param.JccMainPictureParam;
import com.jyyy.picturegeneration.controller.param.MLolMainPictureParam;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface PictureGenerationService {
    BufferedImage generation(Map<String,Object> config);
    BufferedImage generation(MLolMainPictureParam config);
    BufferedImage generation(JccMainPictureParam param);
}
