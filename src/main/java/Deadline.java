public class Deadline extends Task {
    String deadline;

    Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[deadline]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
