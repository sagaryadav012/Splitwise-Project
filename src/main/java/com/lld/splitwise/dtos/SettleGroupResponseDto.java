package com.lld.splitwise.dtos;

import com.lld.splitwise.models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class SettleGroupResponseDto {
    private Response response;
    private List<Transaction> transactions;
}
