package com.wangqing.chilemecilent.object.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DynamicDto {

    public static final Integer POST_EVALUATE = 0;
    public static final Integer POST_FOOD_REC = 1;

    private Integer postType;
    private Integer postId;
    private String headline;
    private Date postTime;
    private String postImage;

    private Integer postUserId;
    private String postUserName;
    private String postUserAvatar;

    private Integer likeNumber;
    private boolean likeStatus;

    private Integer commentNumber;
}
