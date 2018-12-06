package me.maiz.ittrainning.simplecrawlerboot.common;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {

    private boolean success;
    /**
     * 错误码：
     * <table>
     *     <tr><th>错误码</th><th>错误含义</th></tr>
     *     <tr><td>0000</td><td>成功</td></tr>
     *     <tr><td>0001</td><td>未知失败</td></tr>
     *     <tr><td>1001</td><td>用户名不存在或密码不匹配</td></tr>
     *
     * </table>
     */
    private String  code;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        this.message =success?"执行成功":"执行失败";
        this.code=success?"0000":"1000";
    }

    public Result(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Result(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return success(null);
    }

    public static Result success(Object data){
        return new Result(true,"0000","执行成功",data);
    }

    public static Result generalFail(){
        return fail("1000","未知错误");
    }

    public static Result fail(String code,String message){
        return new Result(false,code,message,null);
    }
}
