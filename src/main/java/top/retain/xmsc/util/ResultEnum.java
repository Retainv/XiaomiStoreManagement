package top.retain.xmsc.util;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Retain
 *
 * @date 2021/1/14 15:31
 */
@Getter
@NoArgsConstructor
public enum ResultEnum {
    SUCCESS(1,"请求成功"),
    ERROR(0,"请求错误");


    private int code;
    private String msg;
    private Object data;

    ResultEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ResultEnum(int code, String msg,Object data){
        this(code, msg);
        this.data = data;
    }

}
