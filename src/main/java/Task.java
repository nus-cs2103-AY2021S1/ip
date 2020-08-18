public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // Gets Status of task
    public boolean getStatus() {
        return this.isDone;
    }

    // Sets Status of task
    public void markedDone(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        // By default print task name
        return this.name;
    }
}
