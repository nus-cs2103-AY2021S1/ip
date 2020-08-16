public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        System.out.println(" ____________________________________________________________\n " +
                "Nice! I've marked this task as done:\n" +
                "    [" + "\u2713" + "] " + this.description +
                "\n ____________________________________________________________");
        return new Task(this.description, true);
    }
}
