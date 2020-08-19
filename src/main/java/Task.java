public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
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
