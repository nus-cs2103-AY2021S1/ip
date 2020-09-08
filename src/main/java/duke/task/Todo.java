package duke.task;

/**
 * Represents a to do task with description.
 * Inherits from Task.
 */
public class Todo extends Task {

    /**
     * Initializes with a description of the to do task.
     *
     * @param desc Description.
     */
    public Todo(String desc) {
        super(desc);
        this.type = TaskType.TODO;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String printSaveFormat() {
        return "todo " + super.printSaveFormat();
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Subroutine for deep-copying a to do
     *
     * @param t To do task to be copied.
     * @return Deep copy of the to do task given.
     */
    public static Todo deepCopyTodo(Task t) {
        Todo todoCopy = new Todo(t.description);
        todoCopy.status = t.status;
        return todoCopy;
    }
}
