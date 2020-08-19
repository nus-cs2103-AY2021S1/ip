public class Deadline extends Task {
    String description;

    Deadline(String task, String description) {
        super(task);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.description + ")";
    }
}
