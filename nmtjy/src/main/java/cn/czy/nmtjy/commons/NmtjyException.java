package cn.czy.nmtjy.commons;

import org.springframework.http.HttpStatus;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 20:42
 * @Version 1.0
 */
public class NmtjyException extends RuntimeException{

    private HttpStatus httpStatus;
    private String msg;
    public NmtjyException(String msg){
        this.msg = msg ;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
