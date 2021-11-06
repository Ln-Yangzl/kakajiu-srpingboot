package com.springboot.kakajiu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: 数据库表video_tag的对应实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class VideoTag implements Serializable {

    @TableId
    private Integer tagId;
    private String tagName;

}
