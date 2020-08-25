package main.java;

public class ToDos extends Task {
    private String type = "Todos";
    protected ToDos(String string) {
        super(string.substring(5), string);
    }
    public String toString() {
        return "[T] " + super.toString();
    }
}
