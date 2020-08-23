package main.java;

public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    public Todo(boolean status, String content) {
        super(status, content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "  <-";
    }
}
