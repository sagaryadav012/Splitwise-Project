package com.lld.splitwise.dtos;

import lombok.Data;

@Data
public class Response {
    private String error_msg;
    private ResponseStatus responseStatus;
    public static Response getSuccessResponse(){
        Response response = new Response();
        response.setResponseStatus(ResponseStatus.SUCCESS);
        return response;
    }
    public static Response getFailureResponse(String error_msg){
        Response response = new Response();
        response.setError_msg(error_msg);
        response.setResponseStatus(ResponseStatus.FAILURE);
        return response;
    }
}
