package com.library.seatmanager.dto;

public class DashboardResponse {

    private int totalSeats;
    private long filledSeats;
    private long vacantSeats;

    // 🔥 NEW
    private long halfDayStudents;

    public DashboardResponse(int totalSeats, long filledSeats, long vacantSeats, long halfDayStudents) {
        this.totalSeats = totalSeats;
        this.filledSeats = filledSeats;
        this.vacantSeats = vacantSeats;
        this.halfDayStudents = halfDayStudents;
    }

    public DashboardResponse() {
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public long getFilledSeats() {
        return filledSeats;
    }

    public void setFilledSeats(int filledSeats) {
        this.filledSeats = filledSeats;
    }

    public long getVacantSeats() {
        return vacantSeats;
    }

    public void setVacantSeats(int vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    public long getHalfDayStudents() {
        return halfDayStudents;
    }

    public void setHalfDayStudents(long halfDayStudents) {
        this.halfDayStudents = halfDayStudents;
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "totalSeats=" + totalSeats +
                ", filledSeats=" + filledSeats +
                ", vacantSeats=" + vacantSeats +
                ", halfDayStudents=" + halfDayStudents +
                '}';
    }

}
