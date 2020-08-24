public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description, 'T');
        String unparseMessage = "T";
        if (isDone) {
            unparseMessage += "1";
        } else {
            unparseMessage += "0";
        }
        unparseMessage += description;
        super.unparseMessage = unparseMessage;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
