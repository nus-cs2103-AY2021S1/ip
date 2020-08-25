package main.java.duke;

public class Todo extends Task{

    String taskType = "T";

    /**
     * Create an instance of Todo class
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object.
     * @return a string representation of the Todo object.
     */
    public String toString() {
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "] " + super.description;
    }

}
