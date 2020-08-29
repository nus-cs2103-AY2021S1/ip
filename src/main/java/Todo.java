/**
 * A subclass of Task.
 * Contains a task description and a time.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo object.
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor of Todo object.
     * @param description description of the task.
     * @param isDone the status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks the Todo task as done.
     * @return new Todo object with true for isDone.
     */
    @Override
    public Todo markAsDone() {
        return new Todo(this.description, true);
    }

    /**
     * Turns task object into a string to be saved in data file.
     * @return string in the format of data in data file.
     */
    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "T | " + number + " | " + super.description;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
