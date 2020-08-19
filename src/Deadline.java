public class Deadline extends Task {

    private String deadline;

    public Deadline(String icon, String description, String deadline) {
        super(icon, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
