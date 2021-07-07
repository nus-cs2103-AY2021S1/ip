package main.java.duke;

/**
 * Todo class that encapsulates tasks to be done. This is a child class of Task.
 */
public class Todo extends Task{

    String taskType = "T";

    /**
     * Creates an instance of Todo class
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
        assert super.description.length() > 0 : "Description is empty";
        String priorityString = super.priority != null
                ? "[" + super.priority.toString() + "] "
                : " ";
        return "[" + taskType + "]" + "[" + super.getStatusIcon()
                + "]" + priorityString + super.description;
    }

}
