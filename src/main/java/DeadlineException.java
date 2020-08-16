package main.java;

public class DeadlineException extends Exception {
    DeadlineException() {
        super("☹ OOPS!!! The description or date of a deadline cannot be empty.");
    }
}
