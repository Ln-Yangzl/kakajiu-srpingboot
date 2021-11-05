package com.springboot.kakajiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zlyang
 * @date 2021-11-5
 * @discription response body for /api/getinvitekey
 */
@Data
public class InviteKeyResponse extends SimpleResponseInfo implements Serializable {

    String invitekey;

}
