package main.java;

public class ToDos extends Task {
    String todo;
    protected ToDos(String string) {
        super(string.substring(4));
    }
    public String toString() {
        return "[T] " + super.toString();
    }
}
