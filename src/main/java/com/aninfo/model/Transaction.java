package com.aninfo.model;

import com.aninfo.Enum.TransactionEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double amount;

    private TransactionEnum type;

    public Transaction(Double amount, TransactionEnum type) {
        this.amount = amount;
        this.type = type;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionEnum getType() {
        return type;
    }

    public void setType(TransactionEnum type) {
        this.type = type;
    }

}
