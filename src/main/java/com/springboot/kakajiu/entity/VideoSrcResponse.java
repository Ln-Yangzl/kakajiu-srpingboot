package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zlyang
 * @date 2021-11-2
 * @discription response entity for /getvideourl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoSrcResponse extends SimpleResponseInfo implements Serializable {

    private String videoSrc;

}
