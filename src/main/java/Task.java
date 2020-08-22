public class Task {
    String taskName;
    boolean isCompleted;
    Task(String task) {
        this.taskName = task;
        this.isCompleted = false;
    }

    Task(String task, boolean isCompleted) {
        this.taskName = task;
        this.isCompleted = isCompleted;
    }

    public Task setTaskAsCompleted() {
        return new Task(taskName, true);
    }

    @Override
    public String toString() {
        String tickOrCross  = isCompleted? "DONE" : "NOT DONE";
        return String.format("%s %s %s %s", "[", tickOrCross, "] ", taskName);
    }
}
