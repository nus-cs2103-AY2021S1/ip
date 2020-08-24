package main.java;

public class BobException extends Exception {
    public String getMessage() {
        return "Sorry, but I do not understand your request. Please try again.";
    }
}
