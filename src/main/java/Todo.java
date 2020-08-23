public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        if (super.isDone) return "T | 1 | " + description;
        else return "T | 0 | " + description + " | ";
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
