package com.library.seatmanager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String phone;

    private String password;

    // OTP related
    private String otp;
    private LocalDateTime otpExpiry;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Library> libraries = new ArrayList<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                ", otpExpiry=" + otpExpiry +
                ", libraries=" + libraries +
                '}';
    }

    public Admin() {
    }

    public Admin(Long id, String name, String phone, String password, String otp, LocalDateTime otpExpiry, List<Library> libraries) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.otp = otp;
        this.otpExpiry = otpExpiry;
        this.libraries = libraries;
    }
}
