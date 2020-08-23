package duke.exceptions;

/**
 * Encapsulates the exception where a task out of range of the list is selected for deletion.
 */

public class TaskDeletionException extends DukeException {
    protected int size;
    public TaskDeletionException(int size) {
        this.size = size;
    }

    @Override
    public String getMessage() {
        return "Please select a task from 1 to " + size +".";
    }
}

