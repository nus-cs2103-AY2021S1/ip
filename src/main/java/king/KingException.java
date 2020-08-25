package main.java;

public class KingException extends Exception {
    final String message;
    final Throwable error;
    KingException(String message, Throwable error) {
        this.error = error;
        this.message = message;
    }
}
