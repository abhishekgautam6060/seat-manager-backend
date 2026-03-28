package com.library.seatmanager.dto;

public class LibraryCreateRequest {
    private String libraryName;
    private int totalSeats;
    private String logoUrl;

    public LibraryCreateRequest(String libraryName, String logoUrl, int totalSeats) {
        this.libraryName = libraryName;
        this.logoUrl = logoUrl;
        this.totalSeats = totalSeats;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
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

    public LibraryCreateRequest() {
    }

    @Override
    public String toString() {
        return "LibraryCreateRequest{" +
                "libraryName='" + libraryName + '\'' +
                ", totalSeats=" + totalSeats +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }


}
