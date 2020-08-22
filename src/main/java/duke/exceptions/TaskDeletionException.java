package duke.exceptions;

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

