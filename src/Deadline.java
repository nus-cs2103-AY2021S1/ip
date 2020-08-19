public class Deadline extends Task {
    protected String due;

    public Deadline(String description, int num, String due) {
        super(description, num);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + this.getIcon() + description + " (by: " + due + ")";
    }
}
