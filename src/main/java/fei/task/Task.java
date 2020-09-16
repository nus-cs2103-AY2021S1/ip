package fei.task;

public class Task {
    private static String tick = "\u2713";
    private static String cross = "\u2718";

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

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? tick : cross);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This is a string conversion method.
     *
     * @return the formatted String to be wrote in the text file.
     */
    public String savedFormat() {
        return String.format("| %s | %s", this.isDone, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public boolean isDone() {
        return this.isDone;
    }

}
