package main.java;

public class ToDo extends Task {
    protected String symbol = "[T]";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return symbol + super.toString();
    }
}
