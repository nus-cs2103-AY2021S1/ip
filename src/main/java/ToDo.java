package main.java;

public class ToDo extends Task {
    ToDo(String title) {
        super(title);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
