public class Todo extends Task {
    public static final String taskIcon = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.taskIcon + "]" + super.toString();
    }
}
