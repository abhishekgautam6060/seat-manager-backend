package com.library.seatmanager.dto;

import com.library.seatmanager.entity.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentTableResponse {

    private Long id;
    private String name;
    private String phone;
    private int seatNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDate expireDate;
    private int amount;

    public static StudentTableResponse from(Student s) {
        StudentTableResponse dto = new StudentTableResponse();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setPhone(s.getPhone());
        dto.setSeatNumber(s.getSeatNumber());
        dto.setStartDate(s.getStartDate());
        dto.setEndDate(s.getEndDate());
        dto.setAmount(s.getAmountPaid());
        dto.setExpireDate(s.getExpiryDate());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public StudentTableResponse(Long id, int amount, LocalDate expireDate, LocalDateTime endDate, LocalDateTime startDate, int seatNumber, String phone, String name) {
        this.id = id;
        this.amount = amount;
        this.expireDate = expireDate;
        this.endDate = endDate;
        this.startDate = startDate;
        this.seatNumber = seatNumber;
        this.phone = phone;
        this.name = name;
    }

    public StudentTableResponse() {
    }

    @Override
    public String toString() {
        return "StudentTableResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", seatNumber=" + seatNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                '}';
    }
}
