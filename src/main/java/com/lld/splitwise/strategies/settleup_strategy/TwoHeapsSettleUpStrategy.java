package com.lld.splitwise.strategies.settleup_strategy;

import com.lld.splitwise.models.Transaction;
import com.lld.splitwise.models.User;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class TwoHeapsSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Double> userTotal) {
        PriorityQueue<Pair<User, Double>> maxHeap = new PriorityQueue<>((t1, t2) -> (int) (t2.getSecond() - t1.getSecond()));

        PriorityQueue<Pair<User, Double>> minHeap = new PriorityQueue<>((t1, t2) -> (int) (t1.getSecond() - t2.getSecond()));

        for (Map.Entry<User, Double> entry : userTotal.entrySet()) {
            if(entry.getValue() > 0){
                maxHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
            else{
                minHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        while(!maxHeap.isEmpty() && !minHeap.isEmpty()){
            Pair<User, Double> userToGetMoney = maxHeap.poll();
            Pair<User, Double> userToPay = minHeap.poll();

            double amountToBeTransferred = Math.min(userToGetMoney.getSecond(), Math.abs(userToPay.getSecond()));
            Transaction transaction = new Transaction();
            transaction.setAmount(amountToBeTransferred);
            transaction.setPaidFrom(userToPay.getFirst());
            transaction.setPaidTo(userToGetMoney.getFirst());

            transactions.add(transaction);

            if(userToGetMoney.getSecond() - amountToBeTransferred > 0){
                maxHeap.add(Pair.of(userToGetMoney.getFirst(), userToGetMoney.getSecond() - amountToBeTransferred));
            }

            if(userToPay.getSecond() + amountToBeTransferred < 0){
                minHeap.add(Pair.of(userToPay.getFirst(), userToPay.getSecond() + amountToBeTransferred));
            }
        }
        return transactions;
    }
}
