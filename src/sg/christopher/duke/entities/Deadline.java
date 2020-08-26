package sg.christopher.duke.entities;

public class Deadline extends Task {
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String deadline;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
