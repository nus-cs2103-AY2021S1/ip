package duke.tasks;

/**
 * <code>duke.tasks.Todo</code> inherits from <code>duke.tasks.Task</code>
 * and is used to represent a todo.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a comma seperated <code>String</code> containing the relevant information
     * about this todo. This information will then be saved in a text file.
     * @return the comma seperated text of this todo
     */
    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
;        return "T," + completeStatus + "," + super.getName();
    }

    /**
     * Returns the string representation of this todo.
     * @return a string representation of this todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
