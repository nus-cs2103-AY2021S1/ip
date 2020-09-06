package duke.task;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a special character based on the state of the duke.task.Task.
     * @return a Tick if the task is marked as done or a cross if not.
     */
    public String getStatusIcon() {
        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Marks the duke.task.Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String to be written into saved data.
     * @return a String that contains the necessary information in the correct format.
     */
    public String storageForm() {
        return "";
    }

    public String getDescription() { return this.description; }

    public String getType() { return "Task"; }

    public LocalDate getDate() { return LocalDate.MIN; }

    @Override
    public String toString() {
        return "["+getStatusIcon()+"] " + this.description;
    }

    @Override
    public int compareTo(Task o) {
        if (this instanceof ToDo) {
            return -1;
        } else if (this instanceof Deadline || this instanceof Event) {
            if(o instanceof ToDo) {
                return 1;
            } else {
                return -(o.getDate().compareTo(this.getDate()));
            }
        } else {
            return 0;
        }
    }
}
