package com.zalarfresh.ZalarFresh.Exception;

import java.time.LocalDateTime;

public class ErreurResponse {

    private String message;
    private LocalDateTime timestamp;
    private int status;

    public ErreurResponse(String message, int status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
}
