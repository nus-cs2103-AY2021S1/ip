package main.java;

public class DukeException extends Exception {
    final String message;
    final Throwable error;
    DukeException(String message, Throwable error) {
        this.error = error;
        this.message = message;
    }
}
