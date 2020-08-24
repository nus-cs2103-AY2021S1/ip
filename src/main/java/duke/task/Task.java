package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to use when reading data from the hard disk upon duke.Duke start up.
     * @param description is the details of the task.
     * @param isDone determines whether the task is marked as completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
       return this.getStatusIcon() + " | " + this.description;
    }
}
