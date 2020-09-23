package duke.task;


import java.time.LocalDateTime;

/**
 * <h1> Todo Task class </h1>
 *
 * The Todo class is a child class of Task
 * It does not have any additional properties
 * from a Task Object
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Todo extends Task {

    protected Todo(String task) {
        super(task);
        this.date = LocalDateTime.now();
    }

    /**
     * Creates a Todo object
     * @param task Task to be added
     * @return Todo
     */
    public static Todo createTodo(String task) {
        return new Todo(task);
    }

    /**
     * Returns a string representation of a Todo Object
     * [T][X]** (task) for tasks marked as Important
     * [T][X] (task) for tasks not marked as Important
     * X for Undone Task, O for Done Task.
     * @return String
     */
    @Override
    public String toString() {
        String done = this.done ? "O" : "X";
        if (isImportant) {
            return "[T][" + done + "]** " + this.task;
        } else {
            return "[T][" + done + "] " + this.task;
        }
    }
}
