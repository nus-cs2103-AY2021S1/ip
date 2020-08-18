package main.java;

public class DoneIncompleteException extends Exception {

    @Override
    public String getMessage() {
        return " Oh no! Please specify which task to be marked as done.";
    }
}
