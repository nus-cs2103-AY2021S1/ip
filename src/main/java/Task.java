public class Task {
    String description;
    boolean isDone;

    Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task completeTask() {
        if (this instanceof Deadline) {
            return new Deadline(this.description, true);
        } else if (this instanceof Event) {
            return new Event(this.description, true);
        } else {
            return new Todo(this.description, true);
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[✓]" : "[✗]")+ " " + description;
    }
}
