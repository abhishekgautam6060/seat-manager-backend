package com.library.seatmanager.dto;

public class OtpRequest {
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public OtpRequest(String otp) {
        this.otp = otp;
    }

    public OtpRequest() {
    }

    @Override
    public String toString() {
        return "OtpRequest{" +
                "otp='" + otp + '\'' +
                '}';
    }
}
