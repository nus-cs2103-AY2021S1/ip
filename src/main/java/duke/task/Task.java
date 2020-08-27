package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates information that are shared by all tasks.
 */
public abstract class Task {

    protected boolean isDone = false;
    final String taskData;

    Task(String taskData) {
        this.taskData = taskData;
    }

    public void competeTask() {
        isDone = true;
    }
    
    public boolean isOnSameDay(LocalDate localDate) {
        return false;
    }
    
    public abstract String generateStorageString();

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + taskData;
        } else {
            return "[\u2718] " + taskData;
        }
    }
}
