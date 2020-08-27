public class Todo extends Task {
    String description;
    boolean isDone;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

    @Override
    public String getType() {
        return "T";
    }
}
