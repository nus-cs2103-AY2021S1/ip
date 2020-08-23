package main.java;

public class DeadlineException extends DukeException {
    DeadlineException() {
        super("☹ OOPS!!! The description or date of a deadline cannot be empty.");
    }
}
