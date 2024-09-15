package com.pdf.repository;

import com.pdf.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdIn(List<Long> userIds);

//    @Query("SELECT t FROM Transaction t WHERE t.transactionDateTime >= :startDateTime AND t.transactionDateTime <= :endDateTime AND t.user.id IN :userIds")
//    List<Transaction> findTransactionsWithinDateRangeAndUserIds(
//            @Param("startDateTime") LocalDateTime startDateTime,
//            @Param("endDateTime") LocalDateTime endDateTime,
//            @Param("userIds") List<Long> userIds
//    );
//
//    // Optionally, if you only need the last 30 days and not a custom date range, add this:
//    default List<Transaction> findTransactionsFromLast30Days(List<Long> userIds) {
//        LocalDateTime endDateTime = LocalDateTime.now();
//        LocalDateTime startDateTime = endDateTime.minusDays(30);
//        return findTransactionsWithinDateRangeAndUserIds(startDateTime, endDateTime, userIds);
//    }

    @Query("SELECT t FROM Transaction t WHERE t.user.id IN :userIds AND t.transactionDateTime >= :startDate")
    List<Transaction> findByUserIdsAndLast30Days(
            @Param("userIds") List<Long> userIds,
            @Param("startDate") LocalDateTime startDate
    );

}
