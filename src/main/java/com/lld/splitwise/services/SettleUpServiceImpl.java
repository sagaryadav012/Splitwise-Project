package com.lld.splitwise.services;

import com.lld.splitwise.exceptions.InvalidRequestException;
import com.lld.splitwise.models.*;
import com.lld.splitwise.repositories.GroupExpenseRepository;
import com.lld.splitwise.repositories.GroupRepository;
import com.lld.splitwise.strategies.settleup_strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class SettleUpServiceImpl implements SettleUpService{
    private GroupRepository groupRepository;
    private GroupExpenseRepository groupExpenseRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImpl(GroupRepository groupRepository, GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    @Override
    public List<Transaction> settleGroup(int groupId) throws InvalidRequestException {
        /*
            Validate the group id using DB
            Fetch list of expenses from group expense table using group id
            Condense all the expenses down to user and their final total
            Use strategy to actually come up with list of transactions
         */

        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidRequestException("Invalid group id"));
        List<GroupExpense> groupExpenses = groupExpenseRepository.findAllByGroupId(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();

//        for (Expense expense : expenses) {
//            System.out.println(expense);
//        }

        Map<User, Double> userTotal = new HashMap<>();

        for(Expense expense : expenses){
            for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                User user = expenseUser.getUser();
                int expenseType = expenseUser.getExpenseType().equals(ExpenseType.PAID) ? 1 : -1;
                userTotal.put(user, userTotal.getOrDefault(user, 0D) +
                        (expenseType * expenseUser.getAmount()));
            }
        }

        List<Transaction> transactions = this.settleUpStrategy.settleUp(userTotal);
        return transactions;
    }
}
