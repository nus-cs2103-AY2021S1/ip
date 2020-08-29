package task;

public class Todo extends Task {

    /**
     * Initializes Todo task
     *
     * @param name
     */
    public Todo(String name) {
        super(name, false);
    }

    /**
     * Initializes Todo task
     *
     * @param name
     * @param isDone
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Set the task to true
     **/
    @Override
    public Task setToTrue() {
        return new Todo(this.name, true);
    }

    /**
     * Get the type of the task
     **/
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Get the end of the task
     **/
    @Override
    public String getEnd() {
        return null;
    }

    /**
     * Convert the current task to String
     **/
    @Override
    public String toString() {
        return isDone
            ? "[T][✓] " + this.getName()
            : "[T][✗] " + this.getName();
    }

    /**
     * Override the equals from Object so that it can be used to handle Todo task
     **/
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Todo) {
            Todo temp = (Todo) o;
            return this.name.equals(temp.name) && (this.isDone == temp.isDone);
        } else {
            return false;
        }
    }
}
