package com.wangqing.chilemecilent.object.ao;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommonResult<T> {
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    /* 时间戳 */
    private LocalDateTime timestamp;
    /* 内部结果码 */
    private Integer code;
    /* 结果信息 */
    private String message;
    /* 数据 */
    private T data;

}
