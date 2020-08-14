public class Task {
    private boolean done;
    private String details;

    public Task(String details) {
        this.done = false;
        this.details = details;
    }

    public void markAsDone() {
        this.done = true;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getDetails() {
        return this.details;
    }
}
