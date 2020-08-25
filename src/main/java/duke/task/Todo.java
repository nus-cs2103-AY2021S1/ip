package duke.task;

/**
 * Encapsulates a Todo task.
 *
 * This is simple extension of the Task class without additional attributes.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task that is not yet completed.
     *
     * @param description Description for the task.
     * @throws TaskException If description is empty.
     */
    public Todo(String description) throws TaskException {
        super(description);
    }

    /**
     * Constructs a Todo task with full set of parameters.
     *
     * @param description Description for the task.
     * @param isComplete If the task is completed already.
     * @throws TaskException If the task description is empty.
     */
    public Todo(String description, boolean isComplete) throws TaskException {
        super(description, isComplete);
    }

    /**
     * Converts the stored representation to a Todo instance.
     *
     * @param storageString Representation as given by its own `toStorageString()`.
     * @return New Todo task based on the storage representation.
     * @throws TaskException If the description is empty, or if storage representation cannot be delimited.
     */
    public static Todo parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 2) {
            throw new TaskException("Invalid storage string");
        }
        return new Todo(inputList[0], decodeCompletionFlag(inputList[1]));
    }

    /**
     * Returns the type of the task.
     *
     * @return The Todo task type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Returns the storage representation.
     *
     * @return The storage representation of the Todo instance.
     */
    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + storeCompletionFlag();
    }

    /**
     * Checks if the given object is the same as the current instance.
     *
     * @param o Alternative object to check.
     * @return True if the given object has the description and completion status.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo t = (Todo) o;
            return isComplete == t.isComplete
                    && description.equals(t.description);
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the current instance.
     *
     * @return String representation of the current instance.
     */
    @Override
    public String toString() {
        return printCompletionFlag() + " | T | " + description;
    }
}
