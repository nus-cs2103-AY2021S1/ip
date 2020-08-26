package duke.task;

/**
 * Todo class that represents a todo task for the user
 */
public class Todo extends Task {

    /**
     * Todo Class constructor. Create a new Todo task with task description.
     *
     * @param description give the description of the Todo Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * To String method of Todo Task
     * @return  a String that describes the Todo Task
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
