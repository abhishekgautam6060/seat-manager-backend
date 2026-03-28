package com.library.seatmanager.dto;



public class HeaderInfoResponse {
    private String libraryName;
    private String logoUrl;
    private String adminName;


    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "HeaderInfoResponse{" +
                "libraryName='" + libraryName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }

    public HeaderInfoResponse(String libraryName, String logoUrl, String adminName) {
        this.libraryName = libraryName;
        this.logoUrl = logoUrl;
        this.adminName = adminName;
    }

    public HeaderInfoResponse() {
    }
}
