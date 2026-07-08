package com.project.blog.springbootblog.exceptions;

import java.time.LocalDate;

public class Error {
    private String message;
    private int status;
    private LocalDate date;
    private String cause;

    public Error() {
    }

    public Error(String message, int status, LocalDate date, String cause) {
        this.message = message;
        this.status = status;
        this.date = date;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
