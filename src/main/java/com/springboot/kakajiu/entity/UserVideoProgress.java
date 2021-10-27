package com.springboot.kakajiu.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author zlyang
 * date:2021-10-27
 * discription: 数据库表user_video_progress对应实体类，记录用户观看视频进度
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserVideoProgress implements Serializable {

    private int userId;
    private int videoId;
    private int progress;

}
