package com.pdf.service;

import com.pdf.entity.Transaction;
import com.pdf.entity.User;
import com.pdf.repository.TransactionRepository;
import com.pdf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void createDummyData() {
        // Create 5 users
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("User " + i);
            users.add(userRepository.save(user));
        }

        // Create 100 transactions
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setTransactionFor("Transaction " + (i + 1));
            transaction.setTransactionAmount(random.nextDouble() * 1000);

            // Generate a random date between July and 11th September 2024
            LocalDate startDate = LocalDate.of(2024, 7, 1);
            LocalDate endDate = LocalDate.of(2024, 9, 11);
            long start = startDate.toEpochDay();
            long end = endDate.toEpochDay();
            long randomEpochDay = ThreadLocalRandom.current().nextLong(start, end + 1);

            transaction.setTransactionDateTime(LocalDateTime.of(LocalDate.ofEpochDay(randomEpochDay),
                    LocalTime.of(random.nextInt(24), random.nextInt(60))));

            // Assign the transaction to a random user
            transaction.setUser(users.get(random.nextInt(users.size())));

            transactionRepository.save(transaction);
        }
    }

    public List<User> fetchUsersForBill(int dayOfMonth) {
        List<User> byBillGenerationDate = userRepository.findByBillGenerationDate(dayOfMonth + "");
        return byBillGenerationDate;
    }

}
