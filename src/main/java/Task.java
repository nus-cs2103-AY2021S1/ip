abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public static Task createTask(TaskType type, String details) throws InvalidTaskException {
        switch (type) {
        case Todo:
            return Todo.createTodo(details);
        case Deadline:
            return Deadline.createDeadline(details);
        default: // Event
            return Event.createEvent(details);
        }
    }

    abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
