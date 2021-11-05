package com.springboot.kakajiu.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * @author table teacher_invite_key的实体对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TeacherInviteKey implements Serializable {
    @TableId
    Integer teacherId;
    String inviteKey;
}
