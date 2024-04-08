package com.lld.splitwise.strategies.settleup_strategy;

import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    List<Transaction> settleUp(Map<User, Double> userTotal);
}
