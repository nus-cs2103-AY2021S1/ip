public class Task {

    private String task;
    private int position;
    private boolean done;

    public Task(String task, int position) {
        this.task = task;
        this.position = position;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String mark = done ? "\u2713" : "\u2717";
        return String.format("%d.[%s] %s", position, mark, task);
    }
}
