package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：威哥
 * 描述：永无Bug
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {
    private Integer code;
    private String msg;
    private T data;
    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
