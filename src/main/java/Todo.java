/**
 * A Todo is the simplest form of a Task, without any restrictions on when it occurs or when it is due.
 *
 * @author jingyenloh
 */
public class Todo extends Task {
    private static final String SAVE_STRING = "TODO|%s|%s";

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Todo todo = (Todo) obj;
        return super.isDone == todo.isDone && super.taskName.equalsIgnoreCase(todo.taskName);
    }
}
