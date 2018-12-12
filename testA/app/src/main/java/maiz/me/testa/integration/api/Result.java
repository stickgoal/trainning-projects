package maiz.me.testa.integration.api;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
        this.message = success ? "执行成功" : "执行失败";
        this.code = success ? "0000" : "1000";
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

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(true, "0000", "执行成功", data);
    }

    public static Result generalFail() {
        return fail("1000", "未知错误");
    }

    public static Result fail(String code, String message) {
        return new Result(false, code, message, null);
    }



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
