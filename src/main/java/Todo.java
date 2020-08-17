package main.java;

public class Todo extends Task {

    // Constructor
    public Todo(String description) {
        super(description);
    }

    // Return the type icon
    public String toString() {
        return " [T]" + super.toString();
    }
}
