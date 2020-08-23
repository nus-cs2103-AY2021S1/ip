//@@author CS2103T-week2-project-task3
//Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html
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
    //@@author

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toStoredTextString() {
        String done = (isDone) ? "1" : "0";
        return done + " | " + description;
    }
}