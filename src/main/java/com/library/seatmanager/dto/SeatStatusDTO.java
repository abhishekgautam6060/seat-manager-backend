package com.library.seatmanager.dto;


public class SeatStatusDTO {
    private int seatNumber;
    private boolean occupied;

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public SeatStatusDTO(int seatNumber, boolean occupied) {
        this.seatNumber = seatNumber;
        this.occupied = occupied;
    }

    public SeatStatusDTO() {
    }

    @Override
    public String toString() {
        return "SeatStatusDTO{" +
                "seatNumber=" + seatNumber +
                ", occupied=" + occupied +
                '}';
    }
}
