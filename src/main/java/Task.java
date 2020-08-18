public class Task {

    // task description
    private final String desc;

    // indicates whether the task is done or not
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public String getDescription() {
        return desc;
    }

    public boolean getDoneStatus() {
        return done;
    }

    public void markAsDone() {
        this.done = true;
    }

    private char getStatusIcon() {
        // returns tick or X symbols
        return done ? '\u2713' : '\u2718';
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), getDescription());
    }

}
