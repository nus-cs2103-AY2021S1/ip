/**
 * Encapsulates a task
 */

public class Todo extends Task {

    /**
     * Constructor
     *
     * @param description is the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor
     *
     * @param description is the description of the task
     * @param isDone is whether or not the task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gives the String representation of the Task
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if two Todos are equal
     *
     * @return a boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Todo) {
            Todo otherTodo = (Todo) object;
            return this.description.equals(otherTodo.description)
                        && this.isDone == otherTodo.isDone;
        } else {
            return false;
        }
    }
}
