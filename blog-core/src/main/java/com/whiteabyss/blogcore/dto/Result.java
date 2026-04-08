package com.whiteabyss.blogcore.dto;


import lombok.Data;

@Data
public class Result {

    private String message;
    private Object data;
    private Integer code;


        public static Result success() {
            Result result = new Result();
            result.setCode(200);
            result.setMessage("success");
            return result;
        }

        public static Result success(Object data) {
            Result result = success();
            result.setData(data);
            return result;
        }

        public static Result success(Integer code,Object data) {
            Result result = success();
            result.setData(data);
            return result;
        }

        public static Result error(String message) {
            Result result = new Result();
            result.setCode(500);
            result.setMessage(message);
            return result;
        }

}
