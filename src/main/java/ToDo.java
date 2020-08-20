package main.java;

public class ToDo extends Task {

    public ToDo(String s, Boolean b) {
        super(s, b);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName();
    }
}
