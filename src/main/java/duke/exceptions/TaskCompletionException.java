package duke.exceptions;

/**
 * Encapsulates the exception where a task out of range of the list is selected for completion.
 */

public class TaskCompletionException extends TaskRangeException {
    public TaskCompletionException(int size) {
        super(size);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " for completion.";
    }
}

