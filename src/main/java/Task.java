public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    // presentation:
    // getting from CS2103 week 2 project https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "Done" : "Not done");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
