package main.java;

public class EmptyDeadlineException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description of a deadline cannot be empty.";
    }
}
