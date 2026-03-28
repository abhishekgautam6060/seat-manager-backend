package com.library.seatmanager.dto;


import com.library.seatmanager.entity.Student;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HalfDayStudentResponse {

    private Long id;
    private String name;
    private String phone;

    private Student.HalfDaySlot halfDaySlot;

    private LocalDate bookingDate;
    private LocalDate expiryDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int amount;
    private boolean active;

    // 🔁 Mapper
    public static HalfDayStudentResponse from(Student s) {
        HalfDayStudentResponse r = new HalfDayStudentResponse();

        r.id = s.getId();
        r.name = s.getName();
        r.phone = s.getPhone();
        r.halfDaySlot = s.getHalfDaySlot();

        r.bookingDate = s.getBookingDate();
        r.expiryDate = s.getExpiryDate();
        r.startDate = s.getStartDate();
        r.endDate = s.getEndDate();

        r.amount = s.getAmountPaid();
        r.active = s.isActive();

        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Student.HalfDaySlot getHalfDaySlot() {
        return halfDaySlot;
    }

    public void setHalfDaySlot(Student.HalfDaySlot halfDaySlot) {
        this.halfDaySlot = halfDaySlot;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public HalfDayStudentResponse(Long id, boolean active, int amount, LocalDateTime endDate, LocalDateTime startDate, LocalDate expiryDate, LocalDate bookingDate, Student.HalfDaySlot halfDaySlot, String phone, String name) {
        this.id = id;
        this.active = active;
        this.amount = amount;
        this.endDate = endDate;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.bookingDate = bookingDate;
        this.halfDaySlot = halfDaySlot;
        this.phone = phone;
        this.name = name;
    }

    public HalfDayStudentResponse() {
    }


    @Override
    public String toString() {
        return "HalfDayStudentResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", halfDaySlot=" + halfDaySlot +
                ", bookingDate=" + bookingDate +
                ", expiryDate=" + expiryDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", active=" + active +
                '}';
    }
}
