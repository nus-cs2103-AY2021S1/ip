package main.java;

public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPSIE!! " + super.getMessage();
    }
}
