package com.library.seatmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private int amount;

    private LocalDate expenseDate;

    private String status;

    // ✅ THIS FIELD MUST EXIST
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Library library;

    // ---------- getters & setters ----------

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public String getStatus() {
        return status;
    }

    public Library getLibrary() {
        return library;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Expense(Library library, String status, LocalDate expenseDate, int amount, String category, Long id) {
        this.library = library;
        this.status = status;
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.category = category;
        this.id = id;
    }

    public Expense() {
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", expenseDate=" + expenseDate +
                ", status='" + status + '\'' +
                ", libraryId=" + (library != null ? library.getId() : null) +
                '}';
    }
}
