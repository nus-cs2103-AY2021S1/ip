package duke;

import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs and initializes the attributes of a new Task object.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void setTime(LocalDate time);

    /**
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Makes a task to be completed.
     * @return A new task object with the same description and date but is completed.
     */
    public abstract Task completeTask();

    @Override
    public String toString() {
        return (isDone ? "[V]" : "[X]")
                + " "
                + description;
    }
}
