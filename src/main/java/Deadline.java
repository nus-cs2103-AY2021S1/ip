public class Deadline extends Task {
    public String due;
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + due + ")";
    }
}
