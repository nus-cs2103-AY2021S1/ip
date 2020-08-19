package main.java;

public class ToDos extends Task {
    protected ToDos(String string) {
        super(string.substring(5));
    }
    public String toString() {
        return "[T] " + super.toString();
    }
}
