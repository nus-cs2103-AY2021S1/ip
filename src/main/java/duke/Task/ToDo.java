package duke.Task;

public class ToDo extends Task {
    /**
     * Constructs a ToDo object to represent a ToDo task
     * @param taskDescription description of task
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}
