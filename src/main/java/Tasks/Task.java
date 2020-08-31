package Tasks;

import java.time.format.DateTimeFormatter;

/**
 * Tasks.Task is the parent class of Tasks.Todo, Tasks.Deadline and Tasks.Event.
 * Contains functions used by all 3 subclasses.
 */
public class Task {
    public static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter DATE_FORMAT_IN = DateTimeFormatter.ISO_LOCAL_DATE;
    private String name;
    private Type type;
    private boolean completed = false;

    Task(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return (completed ? "[✓]" : "[✗]") + " " + name;
    }

    public enum Type {
        TODO, DEADLINE, EVENT;
    }
}

