package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zlyang
 * @date 2021-11-2
 * @discription updateTokenResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTokenResponse extends SimpleResponseInfo{
    private String newToken;
}
