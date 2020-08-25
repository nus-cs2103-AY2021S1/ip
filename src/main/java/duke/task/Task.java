package duke.task;

public class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Constructs a task object.
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Returns the task name.
     * @return String indicating name of task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns true if the task is completed, false if not done yet.
     * @return Boolean indicating progress of task.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as done or completed.
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Returns the task in array form.
     * @return String array.
     */
    public String[] taskToArray() {
        String done;
        if(isCompleted) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"Task", done, taskName};
        return str;
    }

    @Override
    public String toString () {
        if (isCompleted) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
