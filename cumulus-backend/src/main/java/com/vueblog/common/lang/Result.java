package com.vueblog.common.lang;

import lombok.Data;

import java.io.Serializable;
// A Lombok annotation that generates
// getters, setters, toString, equals, and hashCode methods automatically.
@Data
public class Result implements Serializable {
    private int code; //
    private String msg;
    private Object data;//
    // Success method with data only
    public static Result succ( Object data){
        return succ(200,"操作成功",data);
    }
    //Success method with code, message, and data
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    // Failure method with message only
    public static Result fail(String msg){

        return fail(400,msg,null);
    }
    // Failure method with message and data
    public static Result fail(String msg,Object data){

        return fail(400,msg,data);
    }
    // Failure method with code, message, and data
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
