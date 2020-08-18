package main.java;

public class DeadlineIncompleteException extends Exception {

    @Override
    public String getMessage() {
        return "Oh no! Please specify the description of a todo.";
    }
}
