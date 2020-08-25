package seedu.duke;

public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}

// consider exception when 2022-9-15 is given instead of 2022-09-15
// index out of bound exception