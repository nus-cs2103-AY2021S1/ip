public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        return "D" + " | " + super.toFileFormat() + " | " + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString() , this.deadline);
    }
}
