package com.example.p3175.db.entity;

import java.math.BigDecimal;

public class RecurringTransaction {
    private int id;
    private int userId;
    private BigDecimal amount;
    private int dayOfMonth;
    private String description;

    public RecurringTransaction(int id, int userId,  BigDecimal amount, int dayOfMonth, String description) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.dayOfMonth = dayOfMonth;
        this.description = description;
    }

    public RecurringTransaction(int userId,  BigDecimal amount, int dayOfMonth, String description) {
        this.userId = userId;
        this.amount = amount;
        this.dayOfMonth = dayOfMonth;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurringTransaction that = (RecurringTransaction) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (dayOfMonth != that.dayOfMonth) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + dayOfMonth;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
