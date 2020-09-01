package com.siawsam.duke;

public class Response {
    private final String message;
    
    Response(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
