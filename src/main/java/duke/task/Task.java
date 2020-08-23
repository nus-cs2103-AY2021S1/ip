package duke.task;

/**
 * Class the simulates the task that user has inputted.
 */

public class Task {

    //@@author Damith C. Rajapakse
    //Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object the containing details of the task.
     * 
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task object the containing details of the task.
     * 
     * @param description Details of the task.
     * @param isDone Boolean value of whether a task is completed.
     */
    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Displays a cross if task is not done, a tick otherwise.
     * 
     * @return Byte encoding strings of the symbols tick or X.
     */
    private String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }
    //@@author

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        if (this.isDone) {
            System.out.println("This duke.task has already been marked!");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this duke.task as done:");
            System.out.println(this);
        }
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     * 
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format(",%s,%d\n", description, getTaskStatus());
    }

    /**
     * If a task is completed, return 1 else 0
     * 
     * @return An integer coded for the boolean status of the task.
     */
    private int getTaskStatus() {
        return isDone ? 1 : 0;
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}