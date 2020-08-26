package main.java.exception;

public class EventException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " The description of a event cannot be empty";
    }
}
