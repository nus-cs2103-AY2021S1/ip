package main.java;

public class EventException extends DukeException {
    EventException() {
        super("☹ OOPS!!! The description or time of an event cannot be empty.");
    }
}
