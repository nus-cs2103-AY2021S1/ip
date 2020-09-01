package seedu.duke;

/**
 * Provide properties of a task to be completed.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Initializes a task class that is not completed.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        task = taskName;
        isDone = false;
    }

    /**
     * Initializes a task class that is not completed.
     *
     * @param taskName Name of the task.
     * @param status Status of the task.
     */
    public Task(String taskName, boolean status) {
        task = taskName;
        isDone = status;
    }

    /**
     * Gets status of a task in a form of a tick or cross.
     *
     * @return Status of a task.
     */
    public String getStatus() {
        return isDone ? "[DONE]" : "[NOT DONE]";
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Name of the task.
     *
     * @return Description of a task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Status of a task in a form of a boolean.
     *
     * @return true or false depending on whether a task is completed.
     */
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getTask();
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
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return task + " ~ " + done + " ~ " + getTask();
    }

    /**
     * Converts a task to a string to be saved locally in a txt file.
     *
     * @param task name of a task.
     * @param date date of a task for Event or Deadline.
     * @return String formatted to be saved in a txt file.
     */
    public String getStorageString(String task, String date) {
        return task + " ~ " + (isDone ? "1" : "0") + " ~ " + getTask() + " ~ " + date;
    }
}
