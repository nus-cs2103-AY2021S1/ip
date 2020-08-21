public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean done, String description) {
        super(done, description, 'T');
        String unparseMessage = "T";
        if (done) {
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
