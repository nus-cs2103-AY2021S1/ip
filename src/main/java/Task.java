import java.util.stream.Stream;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Method to print status icon
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // Method to set task to be completed
    public void markAsDone() {
        this.isDone = true;
    }

    // Method to return status of task
    public boolean getStatus() {
        return this.isDone;
    }

    private int getTaskStatus() {
        return this.isDone ? 1 : 0;
    }

    // Method to print a task
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    protected String inputInFile() {
        if (this instanceof Todo) {
            return "T//" + this.getTaskStatus() + "//" + this.description;
        }
        if (this instanceof Event) {
            return "E//" + this.getTaskStatus() + "//" + this.description + "//" + ((Event) this).getAt();
        }
        if (this instanceof Deadline) {
            return "D//" + this.getTaskStatus() + "//" + this.description + "//" + ((Deadline) this).getBy();
        }
        return " ";
    }
}
