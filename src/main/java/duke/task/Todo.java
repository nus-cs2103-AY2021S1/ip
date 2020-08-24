package duke.task;

public class Todo extends Task {
    public Todo(String description) throws TaskException {
        super(description);
    }

    public Todo(String description, boolean isComplete) throws TaskException {
        super(description, isComplete);
    }

    public static Todo parseStorageString(String storageString) throws TaskException {
        String[] inputList = storageString.split(DELIMITER_STORAGE);
        if (inputList.length < 2) {
            throw new TaskException("Invalid storage string");
        }
        return new Todo(inputList[0], decodeCompletionFlag(inputList[1]));
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public String toStorageString() {
        return description + DELIMITER_STORAGE + storeCompletionFlag();
    }

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

    @Override
    public String toString() {
        return printCompletionFlag() + " | T | " + description;
    }
}
