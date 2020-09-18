package duke;

public class Task {

    protected String description;
    protected boolean isDone;
    protected int sequence;

    /**
     * Returns a Task which takes in an argument of String description
     * This is a constructor of task
     *
     * @return a Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.sequence = 0;
    }

    /**
     * Returns tick or cross based on the task's isDone boolean
     *
     * @return String of tick or cross
     */

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    /**
     * Sets the current task's isDone boolean to true
     *
     */
    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as done and returns a String response to inform the user.
     *
     * @return String of information about the task which is marked as done.
     */
    public String markAsDone () {
        this.isDone = true;
        String doneString = "____________________________________________________________\n"
                + " Nice! I've marked this task as done: \n"
                + "  " + this.getStatusIcon() + "  " + this.description + "\n"
                + "____________________________________________________________";
        return doneString;
    }
    /**
     * Produces a string to represent the task in duke.txt
     *
     * @return String of task
     */
    public String writeToFile() {
        String isDoneString = this.isDone ? " 1 @ " : " 0 @ ";
        return isDoneString + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + "  " + this.description;
    }

}
