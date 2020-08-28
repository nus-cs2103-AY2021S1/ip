/**
 * Todo as a child class of Task
 * contains information such as the task description
 */

public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description of the todo task
     */

    public Todo(String description) {
        super(description);
    }

    @Override

    /**
     * shows the task name, taskIcon
     * @return the string format of the todo
     */
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }
}