package com.siawsam.duke;

public class Response {
    private String message = "";
    private boolean isEmpty = false;
    private boolean isTerminating = false;
    
    Response(String message) {
        this.message = message;
    }
    
    private Response(String message, boolean isTerminating) {
        this.message = message;
        this.isTerminating = isTerminating;
    }
    
    private Response(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    public static Response emptyResponse() {
        return new Response(true);
    }
    
    public static Response terminatingResponse(String message) {
        return new Response(message, true);
    }
    
    public String getMessage() {
        return message;
    }
    
    public boolean isTerminating() {
        return isTerminating;
    }
}
