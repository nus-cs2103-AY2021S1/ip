package main.java;

public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
