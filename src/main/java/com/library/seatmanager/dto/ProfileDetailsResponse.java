package com.library.seatmanager.dto;

public class ProfileDetailsResponse {
    private String adminName;
    private String adminPhone;
    private String libraryName;
    private int totalSeats;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public ProfileDetailsResponse(String adminName, int totalSeats, String libraryName, String adminPhone) {
        this.adminName = adminName;
        this.totalSeats = totalSeats;
        this.libraryName = libraryName;
        this.adminPhone = adminPhone;
    }

    public ProfileDetailsResponse() {
    }

    @Override
    public String toString() {
        return "ProfileDetailsResponse{" +
                "adminName='" + adminName + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", libraryName='" + libraryName + '\'' +
                ", totalSeats=" + totalSeats +
                '}';
    }

}
