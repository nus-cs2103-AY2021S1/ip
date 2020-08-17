import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

abstract class Task {
    protected String description;
    protected boolean isDone = false;
    protected static HashSet<String> taskTypes = new HashSet<>();

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    static {
        taskTypes.addAll(Arrays.asList(TODO, DEADLINE, EVENT));
    }

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public static boolean isTask(String str) {
        return taskTypes.contains(str);
    }

    public static Optional<Task> createTask(String type, String details) {
        switch (type) {
        case (TODO):
            return Optional.of(Todo.createTodo(details));
        case(DEADLINE):
            return Optional.of(Deadline.createDeadline(details));
        case (EVENT):
            return Optional.of(Event.createEvent(details));
        default:
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
