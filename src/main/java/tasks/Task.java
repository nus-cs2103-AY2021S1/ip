package tasks;

abstract class Task {
    public static final String SEPERATOR = "#sep#";
    protected String description;
    protected boolean isDoneTask;

    protected Task(String description, boolean done) {
        this.description = description;
        this.isDoneTask = done;
    }

    /**
     * Returns the check for if the task is already done.
     *
     * @return boolean check
     */
    public boolean done() {
        return this.isDoneTask;
    }

    /**
     * Mark Tasks.Task object as done
     */
    public void doTask() {
        this.isDoneTask = true;
    }

    /**
     * Get the description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checklist icon for the task
     *
     * @return either done or not done
     */
    public String statusIcon() {
        return this.isDoneTask ? "[\u2713] " : "[\u2718] ";
    }

    @Override
    public String toString() {
        return this.statusIcon() + this.getDescription();
    }

    /**
     * Takes done status and attributes to encode the sep
     *
     * @return a encoded string version of task for writing to text file.
     */
    public String saveTask() {
        return this.isDoneTask + SEPERATOR + description;
    }
}
