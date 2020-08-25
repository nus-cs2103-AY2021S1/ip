package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns T to mark this as a todo object.
     * @return T in string.
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns the string representation of this deadline object.
     * @return String object of this deadline.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description;
    }

    /**
     * Mark this task as done.
     * @return Done version of the old task.
     */
    @Override
    public Todo markAsDone() {
        //int index = taskNum - 1;
        if(!this.isDone) {
            Todo newTask = new Todo(this.getDescription(), true);
            return newTask;
        }
        return this;
    }
}