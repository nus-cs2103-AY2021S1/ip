package duke;

/**
 * Class representing tasks to be done
 */
class Todo extends Task {

    /**
     * Constructs a to do.
     *
     * @param description Description for to do.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Constructs a to do.
     *
     * @param description Description for to do.
     * @param isDone Describes if task is completed.
     */
    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string format for file storage.
     *
     * @return String description.
     */
    @Override
    String toStringForStorage() {
        return "T | " + super.toStringForStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
