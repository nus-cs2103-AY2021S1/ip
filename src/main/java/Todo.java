package main.java;

public class Todo extends Task{

    String taskType = "T";

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + super.description;
    }

}
