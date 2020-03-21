package com.wangqing.chilemecilent.object.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private Integer userId;
    private String UserAvatar;
    private String userName;
    private String msg;
    private Date time;
}
