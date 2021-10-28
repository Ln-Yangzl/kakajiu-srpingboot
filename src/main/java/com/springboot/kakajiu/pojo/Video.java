package com.springboot.kakajiu.pojo;


import lombok.*;

import java.io.Serializable;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: video表对应的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Video implements Serializable {

    private int videoId;
    private String title;
    private String videoSrc;
    private String tagName;

}
