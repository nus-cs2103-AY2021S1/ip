package main.java;

public class ToDoException extends Exception {
    ToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
