public class Task {
    String description;
    boolean isDone;

    Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task completeTask() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        return (isDone ? "[✓]" : "[✗]")+ " " + description;
    }
}
