package main.java;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    public Todo(boolean isDone, String description) {super(isDone, description);}
}
