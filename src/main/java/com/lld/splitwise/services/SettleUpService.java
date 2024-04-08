package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.InvalidRequestException;
import com.lld.splitwise.models.Transaction;

import java.util.List;

public interface SettleUpService {
    List<Transaction> settleGroup(int groupId) throws InvalidRequestException;
}
