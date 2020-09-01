public abstract class Task {

    /** Alphabet tag for the respective task **/
    protected String tag;
    /** Boolean variable to check if the task is completed **/
    protected boolean isDone = false;
    /** Name of the task **/
    protected String taskName;

    /**
     *Class constructor
     *
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Mark the task as completed
     *
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Prints add task message for the current task
     *
     */
    public String printAddTask() {
        return String.format("Got it. I've added this task:\n  %s", this.toString());
    }

    /**
     * Prints delete task message for the current task
     *
     */
    public String printDeleteTask() {
        return String.format("Noted. I've removed this task:\n %s", this.toString());
    }


    /**
     * get the string format of the stored task in the hard drive
     *
     * @return String of the stored task
     */
    public String safeFileFormat() {
        return "";
    }

    /**
     * Check if input description is the same as task description
     *
     * @return Boolean on whether the description matches
     */
    public boolean fitsTask(String description) {
        return taskName.contains(description);
    }

}
