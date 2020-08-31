import java.time.format.DateTimeFormatter;

/**
 * Task is the parent class of Todo, Deadline and Event.
 * Contains functions used by all 3 subclasses.
 */
class Task {
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

    enum Type {
        TODO, DEADLINE, EVENT;
    }
}

