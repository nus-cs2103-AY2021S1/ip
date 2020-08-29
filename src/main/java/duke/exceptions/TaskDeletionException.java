package duke.exceptions;

/**
 * Encapsulates the exception where a task out of range of the list is selected for deletion.
 */

public class TaskDeletionException extends TaskRangeException {
    public TaskDeletionException(int size) {
        super(size);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " for deletion.";
    }
}

