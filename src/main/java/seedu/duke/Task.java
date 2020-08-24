package seedu.duke;

/**
 * Provide properties of a task to be completed.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Initialise a task class that is not completed.
     *
     * @param task Name of the task.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Initialise a task class that is not completed.
     *
     * @param task Name of the task.
     * @param isDone Status of the task.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Gets status of a task in a form of a tick or cross.
     *
     * @return Status of a task.
     */
    public String getStatus() {
        return this.isDone ? "[✓]" : "[✘]";
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Name of the task.
     *
     * @return Description of a task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Status of a task in a form of a boolean.
     *
     * @return true or false depending on whether a task is completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.getTask();
    }

    /**
     * Converts a task to a string to be saved locally in a txt file.
     *
     * @param task name of a task.
     *
     * @return String formatted to be saved in a txt file.
     */
    public String getStorageString(String task) {
        String done = "";
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return task + " ~ " + done + " ~ " + this.getTask();
    }

    /**
     * Converts a task to a string to be saved locally in a txt file.
     *
     * @param task name of a task.
     * @param date date of a task for Event or Deadline.
     * @return String formatted to be saved in a txt file.
     */
    public String getStorageString(String task, String date) {
        String done = "";
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return task + " ~ " + done + " ~ " + this.getTask() + " ~ " + date;
    }
}
