public class Deadline extends Task {
    public String due;
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public Deadline(boolean done, String description, String due) {
        super(done, description,'D');
        this.due = due;
        String unparseMessage = "D";
        if (done) {
            unparseMessage += "1";
        } else {
            unparseMessage += "0";
        }
        unparseMessage += description;
        unparseMessage += ",";
        unparseMessage += due;
        super.unparseMessage = unparseMessage;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + due + ")";
    }
}
