package com.wangqing.chilemecilent.object.ao;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonResult<T> {
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    /* 时间戳 */
    private Date timestamp;
    /* 内部结果码 */
    private Integer code;
    /* 结果信息 */
    private String message;
    /* 数据 */
    private T data;

}
