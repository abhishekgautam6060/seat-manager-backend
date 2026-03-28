package com.library.seatmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libraryName;
    private String logoUrl;
    private int totalSeats;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }


    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Library(Long id, int totalSeats, String logoUrl, String libraryName, Admin admin) {
        this.id = id;
        this.totalSeats = totalSeats;
        this.logoUrl = logoUrl;
        this.libraryName = libraryName;
        this.admin = admin;
    }

    public Library() {
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", libraryName='" + libraryName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", totalSeats=" + totalSeats +
                ", admin=" + admin +
                '}';
    }
}
