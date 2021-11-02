package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author zlyang
 * data: 2021-10-29
 * discription: classified videoLists for frontpage
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class FrontpageVideoListData implements Serializable {
    private String tagname;
    private List<String> videos;
}
