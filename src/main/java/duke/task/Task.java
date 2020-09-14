package duke.task;

/**
 * Represents a task with a description, whether the task is done and the priority level.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Initializes a task with only description given.
     * The task's finishing status and priority level
     * will be set to false and low respectively by default.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Initializes a task with description and finishing status given.
     * The task's priority level will be set to low by default.
     *
     * @param description Description of the task.
     * @param isDone      Boolean to indicate whether the task is finished.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.priority = Priority.LOW;
    }

    /**
     * Initializes a task with description, finishing status and priority level given.
     *
     * @param description Description of the task.
     * @param isDone      Boolean to indicate whether the task is finished.
     * @param priority    Priority level of the task.
     */
    public Task(String description, boolean isDone, String priority) {
        this.description = description;
        this.isDone = isDone;
        if (priority.equals("high")) {
            this.priority = Priority.HIGH;
        } else if (priority.equals("medium")) {
            this.priority = Priority.MEDIUM;
        } else {
            this.priority = Priority.LOW;
        }
    }

    /**
     * Gets the finishing status icon of the task.
     *
     * @return ✗ if the task has not been finished.
     *         ✓ if the task is finished.
     */
    public String getStatusIcon() {
        //return ✓ or ✗ symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Checks whether the description of the task contains a certain keyword.
     *
     * @param keyword The keyword to be found.
     * @return True if the task description contains that word and false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        String[] strings = this.description.split(" ");
        for (String word : strings) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            if (obj instanceof Task) {
                Task otherTask = (Task) obj;
                return otherTask.getDescription().equals(this.description);
            } else {
                return false;
            }
        }
    }

    /**
     * Gets the string representation of the task to be stored in the data file.
     *
     * @return String representation of the task in the data file.
     */
    public abstract String getStorageString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
