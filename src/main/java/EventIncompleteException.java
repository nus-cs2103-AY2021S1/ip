package main.java;

public class EventIncompleteException extends Exception {

    @Override
    public String getMessage() {
        return "Oh no! Please specify the description of an event.";
    }
}
