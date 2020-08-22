package main.java;

import java.io.IOException;

public class FailToReadFileException extends IOException {
    FailToReadFileException() {
        super("Something went wrong when reading the storage file! Please try again.");
    }
}
