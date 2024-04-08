package com.lld.splitwise.controllers;

import com.lld.splitwise.dtos.Response;
import com.lld.splitwise.dtos.SettleGroupRequestDto;
import com.lld.splitwise.dtos.SettleGroupResponseDto;
import com.lld.splitwise.exceptions.InvalidRequestException;
import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto requestDto){
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        try{
            if(requestDto.getGroupId() <= 0){
                throw new InvalidRequestException("Group Id is not valid");
            }
            List<Transaction> transactions = this.settleUpService.settleGroup(requestDto.getGroupId());
            responseDto.setTransactions(transactions);
            responseDto.setResponse(Response.getSuccessResponse());
        }
        catch (Exception e){
            Response response = Response.getFailureResponse(e.getMessage());
            responseDto.setResponse(response);
        }
        return responseDto;
    }
}
