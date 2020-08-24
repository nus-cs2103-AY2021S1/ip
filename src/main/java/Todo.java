public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "T" + " | " + completionStatus + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + super.toString();
    }
}