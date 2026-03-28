package com.library.seatmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    private Integer seatNumber;

    private int amount;

    private LocalDate bookingDate;
    private LocalDate expiryDate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int amountPaid;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private StudentType studentType;

    @Enumerated(EnumType.STRING)
    private HalfDaySlot halfDaySlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Library library;

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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public HalfDaySlot getHalfDaySlot() {
        return halfDaySlot;
    }

    public void setHalfDaySlot(HalfDaySlot halfDaySlot) {
        this.halfDaySlot = halfDaySlot;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", seatNumber=" + seatNumber +
                ", amount=" + amount +
                ", bookingDate=" + bookingDate +
                ", expiryDate=" + expiryDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amountPaid=" + amountPaid +
                ", seat=" + seat +
                ", active=" + active +
                ", studentType=" + studentType +
                ", halfDaySlot=" + halfDaySlot +
                ", library=" + library +
                '}';
    }

    public Student() {
    }


    public Student(Long id, String name, String phone, Integer seatNumber, int amount, LocalDate bookingDate, LocalDate expiryDate, LocalDateTime startDate, LocalDateTime endDate, int amountPaid, Seat seat, boolean active, StudentType studentType, HalfDaySlot halfDaySlot, Library library) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.seatNumber = seatNumber;
        this.amount = amount;
        this.bookingDate = bookingDate;
        this.expiryDate = expiryDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amountPaid = amountPaid;
        this.seat = seat;
        this.active = active;
        this.studentType = studentType;
        this.halfDaySlot = halfDaySlot;
        this.library = library;
    }

    public enum StudentType {
        FULL_DAY,
        HALF_DAY
    }
    public enum HalfDaySlot {
        MORNING,   // 6 AM – 2 PM
        EVENING    // 2 PM – 10 PM
    }
}

