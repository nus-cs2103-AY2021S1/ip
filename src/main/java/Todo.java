package main.java;

public class Todo extends Task {

    // Constructor
    public Todo(String description) {
        super(description);
    }

    // Get info to store in hard disk
    @Override
    public String[] getInfo() {
        return new String[] {"T", description};
    }

    // Return the type icon
    public String toString() {
        return " [T]" + super.toString();
    }
}
