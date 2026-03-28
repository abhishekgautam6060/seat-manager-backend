package com.library.seatmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentUpdateRequest {
    private String name;
    private String phone;
    private Integer amountPaid;
    private Integer seatNumber;   // optional
    private LocalDateTime endDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public StudentUpdateRequest(LocalDateTime endDate, String name, String phone, Integer amountPaid, Integer seatNumber, LocalDate expireDate) {
        this.endDate = endDate;
        this.name = name;
        this.phone = phone;
        this.amountPaid = amountPaid;
        this.seatNumber = seatNumber;
        this.expireDate = expireDate;
    }

    public StudentUpdateRequest() {
    }

    @Override
    public String toString() {
        return "StudentUpdateRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", amountPaid=" + amountPaid +
                ", seatNumber=" + seatNumber +
                ", endDate=" + endDate +
                ", expireDate=" + expireDate +
                '}';
    }
}


