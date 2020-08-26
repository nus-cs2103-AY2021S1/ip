package main.java.duke.tasks;

public class Todo extends Task {
    private char type = 'T';
    public Todo(String task) {
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
