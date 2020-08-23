public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadlines(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String writeToFile() { return "D" + super.writeToFile() + " | " + deadline; }
}
