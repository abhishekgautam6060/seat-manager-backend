package com.library.seatmanager.dto;

public class BillingSummaryResponse {

    private int monthlyRevenue;
    private int monthlyExpenses;
    private int monthlyProfit;
    private double averageMonthlyProfit;

    public BillingSummaryResponse(
            int monthlyRevenue,
            int monthlyExpenses,
            int monthlyProfit,
            double averageMonthlyProfit
    ) {
        this.monthlyRevenue = monthlyRevenue;
        this.monthlyExpenses = monthlyExpenses;
        this.monthlyProfit = monthlyProfit;
        this.averageMonthlyProfit = averageMonthlyProfit;
    }

    public int getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(int monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public int getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(int monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public int getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(int monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    public double getAverageMonthlyProfit() {
        return averageMonthlyProfit;
    }

    public void setAverageMonthlyProfit(double averageMonthlyProfit) {
        this.averageMonthlyProfit = averageMonthlyProfit;
    }

}
