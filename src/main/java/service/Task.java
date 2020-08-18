package service;

public abstract class Task {
    String[] tokens;
    protected String description;
    private boolean isDone;

    public Task(String[] tokens) {
        this.isDone = false;
        this.tokens = tokens;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    ///this should calculate description
    public abstract void parse() throws Exception;

    public abstract String getDescription();

    @Override
    public String toString() {
        String status = (!this.isDone ? "[x] ": "[v] ");
        return status + this.getDescription();
    }
}
