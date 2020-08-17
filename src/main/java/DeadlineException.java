package main.java;

public class DeadlineException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " This description of a deadline cannot be empty";
    }
}
