package com.pdf.service;

import com.pdf.entity.Transaction;
import com.pdf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SchedulerService {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "0 0 0 * * *")
    public void fetchBillingUsers() {
        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();
        List<User> users = userService.fetchUsersForBill(dayOfMonth);
        List<Long> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toList());
        Map<Long, List<Transaction>> transactionsForUsersLast30Days = transactionService.getTransactionsForUsersLast30Days(userIds);
        for (Map.Entry<Long, List<Transaction>> entry : transactionsForUsersLast30Days.entrySet()) {
//            for (int i = 0; i < 10000; i++)
            kafkaProducerService.sendMessage(entry.getValue() +"" , entry.getValue());
        }
        System.out.println("Done");
    }


}
