package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int sequence;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.sequence = 0;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public String markAsDone () {
        this.isDone = true;
        String doneString = "____________________________________________________________\n" +
                " Nice! I've marked this task as done: \n" +
                "  "+ this.getStatusIcon() + "  " + this.description + "\n" +
                "____________________________________________________________";
        return doneString;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + "  " + this.description;
    }

}
