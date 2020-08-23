package main.java;

public class InvalidDateFormatException extends DukeException {
    InvalidDateFormatException() {
        super("Invalid date format! Please put it something like 2020-12-31!");
    }
}
