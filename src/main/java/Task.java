public class Task {
    private String task;
    private Boolean completed;
    private int index;

    Task(String task, int index) {
        this.task = task;
        this.completed = false;
        this.index = index;
    }

    Task(String task, int index, Boolean completed) {
        this.task = task;
        this.completed = completed;
        this.index = index;
    }

    public Task updateStatus(Boolean completed) {
        return new Task(this.task, this.index, completed);
    }

    @Override
    public String toString() {
        String completedMarker;

        return String.format("%s", this.task);
    }
}
