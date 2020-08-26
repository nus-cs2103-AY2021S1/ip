package main.java.duke.exception;

public class InvalidCommandException extends DukeException {
    private static String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    @Override
    public String toString() {
        return message;
    }
}
