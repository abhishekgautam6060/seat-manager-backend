package com.library.seatmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.library.seatmanager.entity.Student;
import com.library.seatmanager.entity.Student.HalfDaySlot;
import com.library.seatmanager.entity.Student.StudentType;

public class StudentCreateRequest {

    private String name;
    private String phone;

    private Integer seatNumber;   // null for HALF_DAY

    private int amount;

    private Student.StudentType studentType;   // FULL_DAY / HALF_DAY

    private Student.HalfDaySlot halfDaySlot; 

    private LocalDate bookingDate;
    private LocalDate expiryDate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    
    // required for HALF_DAY

   

    public StudentCreateRequest() {
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Student.StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(Student.StudentType studentType) {
        this.studentType = studentType;
    }

    public Student.HalfDaySlot getHalfDaySlot() {
        return halfDaySlot;
    }

    public void setHalfDaySlot(Student.HalfDaySlot halfDaySlot) {
        this.halfDaySlot = halfDaySlot;
    }

   

    @Override
    public String toString() {
        return "StudentCreateRequest [name=" + name + ", phone=" + phone + ", seatNumber=" + seatNumber + ", amount="
                + amount + ", studentType=" + studentType + ", halfDaySlot=" + halfDaySlot + ", bookingDate="
                + bookingDate + ", expiryDate=" + expiryDate + ", startDate=" + startDate + ", endDate=" + endDate
                + ", getBookingDate()=" + getBookingDate() + ", getExpiryDate()=" + getExpiryDate()
                + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getName()=" + getName()
                + ", getPhone()=" + getPhone() + ", getSeatNumber()=" + getSeatNumber() + ", getAmount()=" + getAmount()
                + ", getStudentType()=" + getStudentType() + ", getHalfDaySlot()=" + getHalfDaySlot() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public StudentCreateRequest(String name, String phone, Integer seatNumber, int amount, StudentType studentType,
            HalfDaySlot halfDaySlot, LocalDate bookingDate, LocalDate expiryDate, LocalDateTime startDate,
            LocalDateTime endDate) {
        this.name = name;
        this.phone = phone;
        this.seatNumber = seatNumber;
        this.amount = amount;
        this.studentType = studentType;
        this.halfDaySlot = halfDaySlot;
        this.bookingDate = bookingDate;
        this.expiryDate = expiryDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
}
