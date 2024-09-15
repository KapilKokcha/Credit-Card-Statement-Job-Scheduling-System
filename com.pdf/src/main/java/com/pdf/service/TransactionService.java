package com.pdf.service;

import com.pdf.entity.Transaction;
import com.pdf.entity.User;
import com.pdf.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findTransactionByUserIds(List<Long> userIds) {
        List<Transaction> byUserIdIn = transactionRepository.findByUserIdIn(userIds);
        return byUserIdIn;
    }

    public Map<Long, List<Transaction>> getTransactionsForUsersLast30Days(List<Long> userIds) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(30);
        List<Transaction> transactions = transactionRepository.findByUserIdsAndLast30Days(userIds, startDate);

        // Group the transactions by userId
        return transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getUser().getId()));
    }


}
