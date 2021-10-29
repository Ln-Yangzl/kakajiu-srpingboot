package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author zlyang
 * data: 2021-10-29
 * discription: classified videoLists for frontpage
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FrontpageVideoListData {
    private String tagName;
    private List<String> videos;
}
