package duke.task;

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

    public boolean contains(String input) {
        return description.contains(input);
    }

    public String getStatusIcon() {
        String sign = isDone ? "\u2713" : "\u2718"; //return tick or X symbols
        return (String.format("[%s]", sign));
    }

    public String getOutput() {
        return String.format("%s%s", getStatusIcon(), this.description);
    }

    public void setDone() {
        isDone = true;
    }

    public String writeToFile() {
        return "-1";
    }
}