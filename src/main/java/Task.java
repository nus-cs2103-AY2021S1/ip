package ip.src.main.java;

public class Task {
    private boolean done;
    protected String description;

    public Task(String task) {
        this.description = task;
        done = false;
    }

    public void markDone() {
        done = true;
    }

    public String getStatusSymbol() {
        return done ? "[\u2713]" : "[\u2718]";
    }
}
