package main.java;

public class Task {
    protected String description;
    protected boolean isDone;
    private final String line = "____________________________________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void finishTask() {
        if (this.isDone) {
            System.out.println("Task is already done!\n" + line );
        } else {
            System.out.println("Congratulations! I have marked this task done.\n" + line );
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}