package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transaction extends BaseModel{
    @ManyToOne(fetch = FetchType.EAGER)
    private User paidFrom;
    @ManyToOne(fetch = FetchType.EAGER)
    private User paidTo;
    private double amount;

    @Override
    public String toString() {
        return "Transaction{" +
                "paidFrom=" + paidFrom.getUsername() +
                ", paidTo=" + paidTo.getUsername() +
                ", amount=" + amount +
                '}';
    }
}
