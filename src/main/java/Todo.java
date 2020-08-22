public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toStore() {
        String div = " | ";
        return "T" + div + (isDone ? "1" : "0") + div + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
