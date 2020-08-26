package main.java;

public class Todo extends Task {
    private char type = 'T';
    Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", type, super.toString());
    }

    @Override
    public String saveToString() {
        return String.format("%c | %s", type, super.saveToString());
    }
}
