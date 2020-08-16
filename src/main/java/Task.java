public class Task {

    private String task;
    private int position;

    public Task(String task, int position) {
        this.task = task;
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", position, task);
    }
}
