package main.java;

public class EmptyTodoException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description of a todo cannot be empty. Format: todo [description]";
    }
}
