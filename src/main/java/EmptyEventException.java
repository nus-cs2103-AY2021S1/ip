package main.java;

public class EmptyEventException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description of an event cannot be empty.";
    }
}
