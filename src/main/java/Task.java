/**
 * Task is the parent class of Todo, Deadline and Event.
 * Contains functions used by all 3 subclasses.
 */
class Task {
    boolean completed = false;
    String name;
    Type type;

    Task(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public void setCompleted() {
        completed = true;
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

    @Override
    public String toString() {
        return (completed ? "[✓]" : "[✗]") + " " + name;
    }

    enum Type {
        TODO, DEADLINE, EVENT;
    }
}

