public class Task {
    protected String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
    }

    public Task doneTask() {
        this.done = true;
        return this;
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return status + " " + task;
    }
}