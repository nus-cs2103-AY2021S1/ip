public class Todo extends Task {

    private String identifier;

    public Todo(String description) {
        super(description);
        this.identifier = "T";
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}