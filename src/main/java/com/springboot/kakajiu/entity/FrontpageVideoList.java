package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zlyang
 * date: 2021-10-28
 * discription: for /api/frontpage responseBody
 */
@ToString
public class FrontpageVideoList extends AbstractResponseBody<FrontpageVideoListData>{

    /**
     * 构造函数
     * @param tagClassifiedVideos key是视频的类别名，值是视频名列表
     */
    public FrontpageVideoList(Map<String, List<String>> tagClassifiedVideos){
        ArrayList<FrontpageVideoListData> frontpageVideoListData = new ArrayList<>();
        tagClassifiedVideos.forEach((key, value) -> {
            frontpageVideoListData.add(new FrontpageVideoListData(key, value));
        });
        this.setData(frontpageVideoListData);
        this.setStatus(0);
    }

}

