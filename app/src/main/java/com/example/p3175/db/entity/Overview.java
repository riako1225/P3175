package com.example.p3175.db.entity;

import java.math.BigDecimal;

public class Overview {
    private int id;
    private int userId;
    private BigDecimal incomes;
    private BigDecimal savings;
    private BigDecimal todayAllowed;
    private BigDecimal todayRemaining;

    public Overview(int id, int userId, BigDecimal incomes, BigDecimal savings, BigDecimal todayAllowed, BigDecimal todayRemaining) {
        this.id = id;
        this.userId = userId;
        this.incomes = incomes;
        this.savings = savings;
        this.todayAllowed = todayAllowed;
        this.todayRemaining = todayRemaining;
    }

    public Overview(int userId, BigDecimal incomes, BigDecimal savings, BigDecimal todayAllowed, BigDecimal todayRemaining) {
        this.userId = userId;
        this.incomes = incomes;
        this.savings = savings;
        this.todayAllowed = todayAllowed;
        this.todayRemaining = todayRemaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getIncomes() {
        return incomes;
    }

    public void setIncomes(BigDecimal incomes) {
        this.incomes = incomes;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }

    public BigDecimal getTodayAllowed() {
        return todayAllowed;
    }

    public void setTodayAllowed(BigDecimal todayAllowed) {
        this.todayAllowed = todayAllowed;
    }

    public BigDecimal getTodayRemaining() {
        return todayRemaining;
    }

    public void setTodayRemaining(BigDecimal todayRemaining) {
        this.todayRemaining = todayRemaining;
    }
}
