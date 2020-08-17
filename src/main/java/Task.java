public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
    }

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }
    public Task doneTask() {
        return new Task(task,true);
    }

    @Override
    public String toString() {
        String status = done ? "[✓]" : "[✗]";
        return status + " " + task;
    }
}