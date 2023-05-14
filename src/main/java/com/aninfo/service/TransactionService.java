package com.aninfo.service;

import com.aninfo.Enum.TransactionEnum;
import com.aninfo.exceptions.AccountNotFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Long id) {return transactionRepository.findTransactionById(id);}

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {

        transactionRepository.deleteById(id);
    }

    @Transactional
    public Transaction transaction(Long cbu, Transaction transaction){
        Account account = accountService.findById(cbu).get();
        transactionRepository.save(transaction);
        account.getTransactions().add(transaction);
        if(transaction.getType() == TransactionEnum.DEPOSIT){
            accountService.deposit(account.getCbu(),transaction.getAmount());
        } else {
            accountService.withdraw(account.getCbu(), transaction.getAmount());
        }
        return transaction;
    }
}
