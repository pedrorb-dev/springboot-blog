package com.project.blog.springbootblog.exceptions;

public class NoEntityFound extends RuntimeException {
    private String message;
    public NoEntityFound(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
