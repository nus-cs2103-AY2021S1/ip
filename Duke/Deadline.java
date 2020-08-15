package Duke;

public class Deadline extends Task {
    private String dueDate;
    public Deadline(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDate + ")";
    }
}
