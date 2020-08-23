package main.java;

public class IncompleteCommandException extends DukeException{

    public IncompleteCommandException(String taskName) {
        super("OOPS!!! The description of a " + taskName + " cannot be empty.");
    }
}