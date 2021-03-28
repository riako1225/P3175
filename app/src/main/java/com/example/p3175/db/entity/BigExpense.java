package com.example.p3175.db.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BigExpense {
    private int id;
    private int userId;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private BigDecimal incomeNeeded;
    private BigDecimal savingNeeded;
    private BigDecimal loanNeeded;

    public BigExpense(int id, int userId, BigDecimal amount, LocalDate date, String description, BigDecimal incomeNeeded, BigDecimal savingNeeded, BigDecimal loanNeeded) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.incomeNeeded = incomeNeeded;
        this.savingNeeded = savingNeeded;
        this.loanNeeded = loanNeeded;
    }

    public BigExpense(int userId, BigDecimal amount, LocalDate date, String description, BigDecimal incomeNeeded, BigDecimal savingNeeded, BigDecimal loanNeeded) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.incomeNeeded = incomeNeeded;
        this.savingNeeded = savingNeeded;
        this.loanNeeded = loanNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigExpense that = (BigExpense) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (incomeNeeded != null ? !incomeNeeded.equals(that.incomeNeeded) : that.incomeNeeded != null)
            return false;
        if (savingNeeded != null ? !savingNeeded.equals(that.savingNeeded) : that.savingNeeded != null)
            return false;
        return loanNeeded != null ? loanNeeded.equals(that.loanNeeded) : that.loanNeeded == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (incomeNeeded != null ? incomeNeeded.hashCode() : 0);
        result = 31 * result + (savingNeeded != null ? savingNeeded.hashCode() : 0);
        result = 31 * result + (loanNeeded != null ? loanNeeded.hashCode() : 0);
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getIncomeNeeded() {
        return incomeNeeded;
    }

    public void setIncomeNeeded(BigDecimal incomeNeeded) {
        this.incomeNeeded = incomeNeeded;
    }

    public BigDecimal getSavingNeeded() {
        return savingNeeded;
    }

    public void setSavingNeeded(BigDecimal savingNeeded) {
        this.savingNeeded = savingNeeded;
    }

    public BigDecimal getLoanNeeded() {
        return loanNeeded;
    }

    public void setLoanNeeded(BigDecimal loanNeeded) {
        this.loanNeeded = loanNeeded;
    }
}
