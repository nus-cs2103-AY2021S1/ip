package duke.task;

public abstract class Task {

    /** Alphabet tag for the respective duke.task **/
    protected String tag;
    /** Boolean variable to check if the duke.task is completed **/
    protected boolean isDone = false;
    /** Name of the duke.task **/
    protected String taskName;

    /**
     *Class constructor
     *
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Mark the duke.task as completed
     *
     */
    public void complete() {
        this.isDone = true;
    }

    public String getTaskName(){
        return taskName;
    }

    /**
     * Prints add duke.task message for the current duke.task
     *
     */
    public String printAddTask() {
        return String.format("Got it. I've added this duke:\n  %s", this.toString());
    }

    /**
     * Prints delete duke.task message for the current duke.task
     *
     */
    public String printDeleteTask() {
        return String.format("Noted. I've removed this duke:\n %s", this.toString());
    }


    /**
     * get the string format of the stored duke.task in the hard drive
     *
     * @return String of the stored duke.task
     */
    public String safeFileFormat() {
        return "";
    }

    /**
     * Check if input description is the same as duke.task description
     *
     * @return Boolean on whether the description matches
     */
    public boolean fitsTask(String description) {
        return taskName.contains(description);
    }

    public boolean isDone(){
        return isDone;
    }

}
