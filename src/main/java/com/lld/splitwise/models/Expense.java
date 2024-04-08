package com.lld.splitwise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Expense extends BaseModel {
    private Date addedAt;
    private double amount;
    private String description;
    private String proofUrl;

    @Enumerated(value = EnumType.ORDINAL)
    private Currency currency;

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<ExpenseUser> expenseUsers;

    @Override
    public String toString() {
        return "Expense{" +
                "addedAt=" + addedAt +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", proofUrl='" + proofUrl + '\'' +
                ", currency=" + currency +
                ", expenseUsers=" + expenseUsers +
                '}';
    }
}
