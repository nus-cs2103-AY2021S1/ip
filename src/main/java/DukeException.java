package main.java;

public class DukeException extends Exception {
    DukeException(String message) {
        super("Error! " + message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
