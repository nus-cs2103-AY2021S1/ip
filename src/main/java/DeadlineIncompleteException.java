package main.java;

public class DeadlineIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify the description of a deadline.";
    }
}
