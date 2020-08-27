package duke.task;

/**
 * Represents to-do task of user.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo object to be stored in file.
     * @return string representation of Todo object for file storage.
     */
    @Override
    public String toFileString() {
        String doneInteger = isDone ? "1" : "0";
        return "T | " + doneInteger + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
