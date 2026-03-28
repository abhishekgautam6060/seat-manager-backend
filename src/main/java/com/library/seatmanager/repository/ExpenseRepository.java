package com.library.seatmanager.repository;

import com.library.seatmanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE e.status = 'PAID'")
    int sumPaidExpenses();


    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM Expense e
                WHERE e.library.id = :libraryId
                  AND YEAR(e.expenseDate) = :year
                  AND MONTH(e.expenseDate) = :month
            """)
    int sumMonthlyExpenses(
            @Param("libraryId") Long libraryId,
            @Param("year") int year,
            @Param("month") int month
    );


    List<Expense> findByLibrary_IdOrderByExpenseDateDesc(Long libraryId);


    // latest expenses first
    List<Expense> findTop10ByLibrary_IdOrderByExpenseDateDesc(Long libraryId);
}
