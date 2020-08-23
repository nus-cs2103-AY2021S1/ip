package main.java;

public class ToDoException extends DukeException {
    ToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
