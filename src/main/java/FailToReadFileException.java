package main.java;

public class FailToReadFileException extends DukeException {
    FailToReadFileException() {
        super("Something went wrong when reading the storage file! Please try again.");
    }
}
